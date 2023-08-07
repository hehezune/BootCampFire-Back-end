package com.ssafy.campfire.bootcamp.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.domain.dto.Data;
import com.ssafy.campfire.bootcamp.domain.dto.Script;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampNameListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.repository.*;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.swing.event.CellEditorListener;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ssafy.campfire.category.domain.enums.CategoryType.BOOTCAMP;

@RequiredArgsConstructor
@Service
@Transactional
public class BootcampService {


    private final BootTrackService bootTrackService;
    private final BootLanguageServie bootLanguageServie;
    private final BootRegionService bootRegionService;

    private final BootcampRepository bootcampRepository;
    private  final CategoryRepository categoryRepository;
    private  final BootLanguageRepository bootLanguageRepository;
    private final BootTrackRepository bootTrackRepository;
    private final BootRegionRepository bootRegionRepository;

    private static String BoottentList_Url ="https://boottent.sayun.studio/camps";

    public BootcampResponseDto save(BootcampRequestDto bootcampRequestDto) {
        Bootcamp bootcamp = bootcampRepository.save(bootcampRequestDto.toBootcamp());

        List<Track> trackList = bootTrackService.save(bootcamp, bootcampRequestDto);
        List<Language> languageList = bootLanguageServie.save(bootcamp, bootcampRequestDto);
        List<Region> regionList = bootRegionService.save(bootcamp, bootcampRequestDto);

        //부트캠프 등록시 카테고리 테이블에도 추가 되도록 하기
        categoryRepository.save(new Category(BOOTCAMP, bootcamp));

        return BootcampResponseDto.of(Optional.ofNullable(bootcamp), Optional.ofNullable(trackList), Optional.ofNullable(languageList), Optional.ofNullable(regionList));
    }


    //부트텐트에서 부트캠프 크롤링하기
    public List<BootcampResponseDto> saveFromBoottent() throws IOException {

        Document document= Jsoup.connect(BoottentList_Url).get();

        Elements elements = document.select("#__NEXT_DATA__");

        String json = elements.get(0).data();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);   //선언한 필드만 매핑
        Script script = objectMapper.readValue(json, Script.class);

        List<Data> dataList = script.getProps().getPageProps().getData().stream().toList();




        //------
        Data data = dataList.get(0);
        String detail_Url = BoottentList_Url + data.detailUrl();
        document = Jsoup.connect(detail_Url).get();
//        System.out.println("document = " + document);
        elements = document.select("#__NEXT_DATA__");

        String jjson = elements.get(0).data();
        System.out.println("jjson = " + jjson);

        script = objectMapper.readValue(jjson, Script.class);
        System.out.println("script = " + script.getProps().getPageProps().getCamp());


        System.out.println();

//        for (Data data :dataList) {
//            String detail_Url = BoottentList_Url + data.detailUrl();
//            document = Jsoup.connect(detail_Url).get();
//            String siteUrl = document.select("#__next > div > section > main > div.container.full-width > div:nth-child(1) > div.camp_layer1__y_wsY > div > div:nth-child(4) > div > a").attr("abs:href");
//            System.out.println("siteUrl = " + siteUrl);
//
//        }
        

        return null;
        
    }

    public BootcampResponseDto getBootcamp(Long bootcampId){
        Optional<Bootcamp> bootcamp = bootcampRepository.findById(bootcampId);
        Optional<List<Track>> trackList = bootTrackService.getTrackListByBootcampId(bootcampId);
        Optional<List<Language>>languageList = bootLanguageServie.getLanguageListByBootcampId(bootcampId);
        Optional<List<Region>> regionList = bootRegionService.getRegionListByBootcampId(bootcampId);
        return BootcampResponseDto.of(bootcamp, trackList,languageList, regionList);
    }

    public BootcampResponseDto updateBootcamp(Long bootcampId, BootcampRequestDto bootcampRequestDto) {

        Bootcamp bootcamp = bootcampRepository.findById(bootcampId).orElseThrow(() -> new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        //부트캠프 정보 업데이트
        bootcamp.update(bootcampRequestDto.toBootcamp());

        //해당 부트캠프에 해당하는 부트트랙, 부트언어, 부트지역 삭제
        bootTrackService.deleteBootTrack(bootcampId);
        bootLanguageServie.deleteBootLanguage(bootcampId);
        bootRegionService.deleteBootRegion(bootcampId);

        //RequestDto에 있는 부트트랙, 부트언어, 부트지역 다시 재저장
        List<Track> trackList = bootTrackService.save(bootcamp, bootcampRequestDto);
        List<Language> languageList = bootLanguageServie.save(bootcamp, bootcampRequestDto);
        List<Region> regionList = bootRegionService.save(bootcamp, bootcampRequestDto);

        return BootcampResponseDto.of(Optional.ofNullable(bootcamp), Optional.ofNullable(trackList), Optional.ofNullable(languageList), Optional.ofNullable(regionList));
    }

    public Long deleteBootcamp(Long bootcampId) {
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(()->new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        Category category = categoryRepository.findCategoryByBootcamp(bootcamp).get();

        categoryRepository.delete(category);

        bootTrackService.deleteBootTrack(bootcamp.getId());
        bootLanguageServie.deleteBootLanguage(bootcamp.getId());
        bootRegionService.deleteBootRegion(bootcamp.getId());

        bootcampRepository.delete(bootcamp);
        return bootcamp.getId();
    }

    //부트캠프를 이름 순으로 정렬
    @Transactional //트랜잭션 범위는 유지하되 기능을 조회로 제한함으로써 조회 속도가 개선
    public List<BootcampListResponseDto> getBootcampListOrderByName() {
        //부트캠프를 이름 순으로 정렬
        List<Bootcamp> bootcampList = bootcampRepository.findAllByOrderByName();

        //각 부트캠프엔티티마다 지역, 트랙을 찾아 responseDto의 리스트로 만들기
        List<BootcampListResponseDto> bootcampListResponseDtoList = new ArrayList<>();

        for (Bootcamp bootcamp: bootcampList) {
            Optional<List<Track>> trackList = bootTrackService.getTrackListByBootcampId(bootcamp.getId());
            Optional<List<Region>> regionList = bootRegionService.getRegionListByBootcampId(bootcamp.getId());

            bootcampListResponseDtoList.add(BootcampListResponseDto.of(Optional.of(bootcamp), trackList, regionList ));
        }

        return bootcampListResponseDtoList;
    }


    // 부트캠프 리스트를 평점 순으로 보기
    @Transactional //트랜잭션 범위는 유지하되 기능을 조회로 제한함으로써 조회 속도가 개선
    public List<BootcampListResponseDto> getBootcampListOrderByScore() {
        //부트캠프를 이름 순으로 정렬
        List<Bootcamp> bootcampList = bootcampRepository.findAllByOrderByScoreDesc();
        System.out.println("사이즈 : " + bootcampList.size());

        //각 부트캠프엔티티마다 지역, 트랙을 찾아 responseDto의 리스트로 만들기
        List<BootcampListResponseDto> bootcampListResponseDtoList = new ArrayList<>();
        for (Bootcamp bc : bootcampList) {
            System.out.println("bc.getName() = " + bc.getName() +" // bc.getTotalScore"+bc.getTotalScore());

        }
        for (Bootcamp bootcamp: bootcampList) {
            Optional<List<Track>> trackList = bootTrackService.getTrackListByBootcampId(bootcamp.getId());
            Optional<List<Region>> regionList = bootRegionService.getRegionListByBootcampId(bootcamp.getId());

            bootcampListResponseDtoList.add(BootcampListResponseDto.of(Optional.of(bootcamp), trackList, regionList ));
        }

        return bootcampListResponseDtoList;
    }
    //    부트캠프 리스트를 리뷰 개수 순으로 보기
    @Transactional //트랜잭션 범위는 유지하되 기능을 조회로 제한함으로써 조회 속도가 개선
    public List<BootcampListResponseDto> getBootcampListOrderByReview() {
        //부트캠프를 이름 순으로 정렬
        List<Bootcamp> bootcampList = bootcampRepository.findAllByOrderByReviewCntDesc();

        //각 부트캠프엔티티마다 지역, 트랙을 찾아 responseDto의 리스트로 만들기
        List<BootcampListResponseDto> bootcampListResponseDtoList = new ArrayList<>();
        for (Bootcamp bc : bootcampList) {
            System.out.println("bc.getName() = " + bc.getName() +" // bc."+bc.getReviewCnt());

        }
        for (Bootcamp bootcamp: bootcampList) {
            Optional<List<Track>> trackList = bootTrackService.getTrackListByBootcampId(bootcamp.getId());
            Optional<List<Region>> regionList = bootRegionService.getRegionListByBootcampId(bootcamp.getId());

            bootcampListResponseDtoList.add(BootcampListResponseDto.of(Optional.of(bootcamp), trackList, regionList ));
        }

        return bootcampListResponseDtoList;
    }

    //부트캠프 이름으로 검색
    @Transactional
    public List<BootcampListResponseDto> getBootcampByBootcampName(String bootcampName) {
        List<BootcampListResponseDto> bootcampListResponseDtoList =  new ArrayList<>();

        Optional<Bootcamp> optionalBootcamp = bootcampRepository.findByName(bootcampName);
        Bootcamp bootcamp = optionalBootcamp.get();
        Optional<List<Track>> trackList = bootTrackRepository.getBootTracksByBootcampId(bootcamp.getId());
        Optional<List<Region>> regionList = bootRegionRepository.getBootRegionsByBootcampId(bootcamp.getId());

        bootcampListResponseDtoList.add(BootcampListResponseDto.of(optionalBootcamp, trackList, regionList));
        return bootcampListResponseDtoList;
    }

    //부트캠프 이름 목록 가져오기
    public List<BootcampNameListResponseDto> getBootcampNameList(){
        Optional<List<Bootcamp>> bootcampList = Optional.of(bootcampRepository.findAll());
        List<BootcampNameListResponseDto> nameList = new ArrayList<>();
        for (Bootcamp bootcamp : bootcampList.get()) {
            nameList.add(new BootcampNameListResponseDto(bootcamp.getId(), bootcamp.getName()));
        }
        return nameList;
    }

    //부트캠프 algo_cnt 초기화
    public void bootcampAlgoCntInit() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
        List<Bootcamp> bootcampList = bootcampRepository.findAll();
        for (Bootcamp bootcamp : bootcampList ){
            bootcamp.setAlgoCnt(0);
        }

    }


}
