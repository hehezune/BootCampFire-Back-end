package com.ssafy.campfire.algorithm.service;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.algorithm.dto.request.AlgorithmRequestDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmResponseDto;
import com.ssafy.campfire.algorithm.repository.AlgorithmRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class AlgorithmService {
    private final AlgorithmRepository algorithmRepository;
    private static String AlgorithmUrl ="https://www.acmicpc.net/problem/";

//    private static String Result_Url = "https://www.acmicpc.net/status?option-status-pid=on&problem_id=1000&user_id=dksek3050&language_id=-1&result_id=-1&from_problem=1"; //크롤링할 url

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





//    public List<Algorithm> getAlgoDatas() throws IOException{
//        List<Algorithm> algoList = new ArrayList<>();
//
//        Document document = Jsoup.connect(URL).get();
//        Elements contents = document.select("#status-table > tbody");
//        System.out.println("contents = " + contents);
//        for(Element content : contents){
//            Algorithm algorithm = Algorithm.builder()
//                    .bojId(content.select("td:nth-child(2) > a").text())
//                    .result(content.select("td.result > span.result-text.result-ac").text())
//                    .solveDate(content.select("td:nth-child(9) > a").attr("title"))
//                            .build();
//            System.out.println("algorithm.toString() = " + algorithm.toString());
//            algoList.add(algorithm);
//        }
//
//        return algoList;
//    }


    private Algorithm getAlgorithmFromBoj(Algorithm algorithm, Long algorithmNum) throws IOException {
        String URL = AlgorithmUrl + algorithmNum;

        Document document = Jsoup.connect(URL).get();

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
