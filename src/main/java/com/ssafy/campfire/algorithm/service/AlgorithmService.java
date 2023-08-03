package com.ssafy.campfire.algorithm.service;

import com.ssafy.campfire.algorithm.domain.AlgoFiftyRank;
import com.ssafy.campfire.algorithm.domain.AlgoManyRank;
import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.algorithm.domain.dto.AlgorithmResult;
import com.ssafy.campfire.algorithm.dto.request.AlgorithmRequestDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmListResponseDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmResponseDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmResultResponseDto;
import com.ssafy.campfire.algorithm.repository.AlgoFiftyRankRepository;
import com.ssafy.campfire.algorithm.repository.AlgoManyRankRepository;
import com.ssafy.campfire.algorithm.repository.AlgorithmRepository;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.global.login.PrincipalDetails;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class AlgorithmService {
    private static String AlgorithmUrl ="https://www.acmicpc.net/problem/";
    private static String Result_Url = "https://www.acmicpc.net/status?option-status-pid=on&problem_id=%ProblemId%&user_id=%UserId%&language_id=-1&result_id=-1&from_problem=1"; //크롤링할 url


    private final AlgorithmRepository algorithmRepository;
    private final UserRepository userRepository;
    private final AlgoManyRankRepository algoManyRankRepository;
    private final AlgoFiftyRankRepository algoFiftyRankRepository;


    public AlgorithmResponseDto save(AlgorithmRequestDto algorithmRequestDto) throws IOException {
        if(algorithmRepository.findAlgorithmByDate(algorithmRequestDto.date()).isPresent()){
            throw new BusinessException(ErrorMessage.DUPLICATE_ALGORITHM_REQUEST);
        }
        Algorithm algorithm = algorithmRequestDto.toAlgorithm();

        algorithm = getAlgorithmFromBoj( algorithm ,algorithmRequestDto.num());

        return AlgorithmResponseDto.from(algorithmRepository.save(algorithm));
    }

    public AlgorithmResponseDto updateAlgorithm(AlgorithmRequestDto algorithmRequestDto, Long algorithmId) throws IOException {
        Algorithm algorithm = algorithmRepository.findById(algorithmId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.ALGORITHM_NOT_FOUND));

        if(algorithmRepository.findAlgorithmByDate(algorithmRequestDto.date()).isPresent()){
            if(algorithmRepository.findAlgorithmByDate(algorithmRequestDto.date()).get().getId() != algorithmId) {
                throw new BusinessException(ErrorMessage.DUPLICATE_ALGORITHM_REQUEST);
            }
        }

        if(algorithm.getNum() != algorithmRequestDto.num()){
            algorithm = getAlgorithmFromBoj(algorithm, algorithmRequestDto.num());
        }

        algorithm.updateAlgorithm(algorithmRequestDto.num(), algorithmRequestDto.date());

        return AlgorithmResponseDto.from(algorithm);
    }


    public Long deleteAlgorithm(Long algorithmId) {
        Algorithm algorithm = algorithmRepository.findById(algorithmId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.ALGORITHM_NOT_FOUND));
        algorithmRepository.delete(algorithm);
        return algorithm.getId();
    }

    public AlgorithmResponseDto getAlgorithm() {
        Algorithm algorithm = algorithmRepository.findAlgorithmByDate(LocalDate.now())
                .orElseThrow(() -> new BusinessException(ErrorMessage.ALGORITHM_NOT_FOUND));
        return AlgorithmResponseDto.from(algorithm);
    }

    public List<AlgorithmListResponseDto> getAlgorithmList() {
        List<Algorithm> algorithmList = algorithmRepository.findAllByOrderByDateDesc();

        List<AlgorithmListResponseDto> algorithmListResponseDtoList = new ArrayList<>();
        for (Algorithm algorithm : algorithmList) {
            System.out.println("algorithm.toString() = " + algorithm.toString());
            algorithmListResponseDtoList.add(AlgorithmListResponseDto.from(algorithm));

        }
        return algorithmListResponseDtoList;
    }

    public AlgorithmResultResponseDto checkAlgorithmResult(PrincipalDetails LoginUser, Long algorithmNum) throws IOException {

//        Integer ranking = algoFiftyRankRepository.findFirstRankByOrderByCreatedDateDesc();
//        System.out.println("ranking = " + ranking);


//        User user = userRepository.findUserById(LoginUser.getId());
        User user = userRepository.findUserById(3L);

        String URL =  Result_Url.replace("%ProblemId%", algorithmNum.toString())
                .replace("%UserId%", user.getBojId());

        //오늘 시간내로 맞았습니다 가 있는지 확인
        if(!isSolved(URL)) return new AlgorithmResultResponseDto(user.getId(), algorithmNum, false);

        // 사용자의 최근 푼 문제 번호가 오늘 문제와 같은지 확인 같다면 갱신하지 않고 끝끝
        if(user.getLatestAlgoNum().equals(algorithmNum)) return new AlgorithmResultResponseDto(user.getId(), algorithmNum, true);


        // 같지 않다면 user 테이블의 오늘 문제 번호로 갱신
        user.updateLatestAlgoNum(algorithmNum);

        Bootcamp bootcamp = user.getBootcamp();
        Algorithm algorithm = algorithmRepository.findAlgorithmByDate(LocalDate.now())
                .orElseThrow(() -> new BusinessException(ErrorMessage.ALGORITHM_NOT_FOUND));

        // 알고 매니 랭크 테이블에 사용자, 부트캠프, 알고리즘 해서 데이터 추가
        algoManyRankRepository.save(new AlgoManyRank(user, bootcamp, algorithm));

        // 부트캠프 테이블의 사용자가 소속한 알고리즘 cnt 증가
        bootcamp.addAlgoCnt();

        // 알고리즘 cnt가 50이라면 피프티 랭크 테이블에 생성일자가 오늘로 하는 가장 큰 수 rank를 가지고 와서 순위 증가하고 데이터 추가
        if(bootcamp.getAlgoCnt() == 50){
            //가장 최근에 저장된 데이터를 가지고 옴
            AlgoFiftyRank algoFiftyRank = algoFiftyRankRepository.findFirstByOrderByCreatedDateDesc();

            //50인 랭크가 비어있거나 가장 최근에 저장된 데이터가 어제 생성된거면 rank를 1로 지정함.
            Integer ranking = 0;
            if(algoFiftyRank == null || algoFiftyRank.createdDate.isBefore(LocalDate.now().atStartOfDay())){
                ranking = 1;
            }else{
                ranking = algoFiftyRank.getRank()+1;
            }

            algoFiftyRankRepository.save(new AlgoFiftyRank(bootcamp, algorithm , ranking));

        }

        return new AlgorithmResultResponseDto(user.getId(), algorithmNum, true);
    }

    private boolean isSolved(String URL) throws IOException {
        // 크롤링 한 결과 가지고 오기
        List<AlgorithmResult> algoList = getAlgorithmResult(URL);
        for (AlgorithmResult algorithmResult : algoList) {
            LocalDate date = LocalDate.parse(algorithmResult.solveDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            //오늘 날짜인지 확인
            if (date.isEqual(LocalDate.now())) {
                //맞았습니다가 있는지 확인
                if (algorithmResult.result().equals("맞았습니다!!")) return  true;
            } else {
                return false;
            }
        }
        return false;

    }

    private List<AlgorithmResult> getAlgorithmResult(String URL) throws IOException {

        // 크롤링 --------------------
        List<AlgorithmResult> algoList = new ArrayList<>();

        Document document = Jsoup.connect(URL).get();
        Elements contents = document.select("#status-table > tbody > tr");

        for(Element content : contents){
            AlgorithmResult algorithmResult = AlgorithmResult.builder()
                    .userBojId(content.select("td:nth-child(2) > a").text())
                    .result(content.select("td.result > span.result-text").text())
                    .solveDate(content.select("td:nth-child(9) > a").attr("title"))
                    .build();
            algoList.add(algorithmResult);
        }
        //-----------------------------

        return algoList;
    }


    private Algorithm getAlgorithmFromBoj(Algorithm algorithm, Long algorithmNum) throws IOException {
        String URL = AlgorithmUrl + algorithmNum;
        Document document;
        try{
            document = Jsoup.connect(URL).get();
        }catch (HttpStatusException e){
            throw new BusinessException(ErrorMessage.INVALID_ALGORITHM_REQUEST);
        }

        Elements titleElements = document.select("div.col-md-12 > div.page-header");
        String title = titleElements.get(0).text();

        Elements descriptionElements = document
                .select("div#problem-body")
                .select("div:nth-child(1)")
                .select("section#description")
                .select("div#problem_description > p");
        System.out.println("descriptionElements = " + descriptionElements);
        String description  = iterElement(descriptionElements);
        description = replaceTag(description);

        algorithm.setAlgorithmContents(URL, title, description);

        return algorithm;
    }
    private String iterElement(Elements elements) {
        //문제 설명이 p 태그 여러개에 나눠 들어가 있음 -> iterator 순회하여 스트링 빌더로 합침
        StringBuilder sb = new StringBuilder();
        for (Element row: elements ){
            Iterator<Element> iterElem = row.getElementsByTag("p").iterator();
            sb.append(iterElem.next().text()+"\n");
        }

        //마지막 줄 바꿈 지우기
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    private String replaceTag(String description) {
        String ret;
        //줄바꿈 치환
        ret = description.replace("&nbsp;", "\r\n");
        //html태그들 치환
        ret = ret.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        return ret;
    }



}


