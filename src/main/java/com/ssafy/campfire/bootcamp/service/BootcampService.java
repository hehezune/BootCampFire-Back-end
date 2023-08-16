package com.ssafy.campfire.bootcamp.service;


import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.utils.crawling.BootcampCrawling;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampNameListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.repository.*;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.ssafy.campfire.utils.crawling.dto.Data;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
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
    private final BootLanguageServie bootLanguageService;
    private final BootRegionService bootRegionService;

    private final BootcampRepository bootcampRepository;
    private  final CategoryRepository categoryRepository;

    private final BootTrackRepository bootTrackRepository;
    private final BootRegionRepository bootRegionRepository;

    public BootcampResponseDto save(BootcampRequestDto bootcampRequestDto) {
        Bootcamp bootcamp = bootcampRepository.save(bootcampRequestDto.toBootcamp());

        List<Track> trackList = bootTrackService.save(bootcampRequestDto.toBootTrackList(bootcamp));
        List<Language> languageList = bootLanguageService.save(bootcampRequestDto.toBootLanguageList(bootcamp));
        List<Region> regionList = bootRegionService.save(bootcampRequestDto.toBootRegionList(bootcamp));

        //부트캠프 등록시 카테고리 테이블에도 추가 되도록 하기
        categoryRepository.save(new Category(BOOTCAMP, bootcamp));

        return BootcampResponseDto.of(Optional.ofNullable(bootcamp), Optional.ofNullable(trackList), Optional.ofNullable(languageList), Optional.ofNullable(regionList));
    }


    //부트텐트에서 부트캠프 크롤링하기
    @Transactional
    public List<BootcampListResponseDto> saveByCrawling() throws IOException, ParseException {

        List<Data> dataList = BootcampCrawling.crawlingBootcamp();

        if(dataList == null) return null;
        for (Data crawlingData : dataList){
            Bootcamp bootcamp = crawlingData.toBootcamp();
            if(bootcampRepository.existsBootcampByName(bootcamp.getName())) {
                //이미 부트캠프가 DB에 존재한다면 description, 트랙, 언어 추가
                Optional<Bootcamp> optionalBootcamp = bootcampRepository.findByName(bootcamp.getName());
                Bootcamp originBootcamp = optionalBootcamp.get();

                originBootcamp.updateDescription(bootcamp.getDescription());

                List<Track> trackList = bootTrackService.updateByCrawling(originBootcamp, crawlingData.getCategories());
                List<Language> languageList = bootLanguageService.updateByCrawling(originBootcamp, crawlingData.getKeywords());
            }else{
                bootcampRepository.save(bootcamp);

                List<Track> trackList = bootTrackService.saveByCrawling(crawlingData, bootcamp);
                List<Language> languageList = bootLanguageService.saveByCrawling(crawlingData, bootcamp);
                List<Region> regionList = bootRegionService.saveByCrawling(crawlingData, bootcamp);

                categoryRepository.save(new Category(BOOTCAMP, bootcamp));
            }
        }

        return getBootcampListOrderByName();

    }

    public BootcampResponseDto getBootcamp(Long bootcampId){
        Optional<Bootcamp> bootcamp = bootcampRepository.findById(bootcampId);
        Optional<List<Track>> trackList = bootTrackService.getTrackListByBootcampId(bootcampId);
        Optional<List<Language>>languageList = bootLanguageService.getLanguageListByBootcampId(bootcampId);
        Optional<List<Region>> regionList = bootRegionService.getRegionListByBootcampId(bootcampId);
        return BootcampResponseDto.of(bootcamp, trackList,languageList, regionList);
    }

    public BootcampResponseDto updateBootcamp(Long bootcampId, BootcampRequestDto bootcampRequestDto) {

        Bootcamp bootcamp = bootcampRepository.findById(bootcampId).orElseThrow(() -> new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        //부트캠프 정보 업데이트
        bootcamp.update(bootcampRequestDto.toBootcamp());

        //해당 부트캠프에 해당하는 부트트랙, 부트언어, 부트지역 삭제
        bootTrackService.deleteBootTrack(bootcampId);
        bootLanguageService.deleteBootLanguage(bootcampId);
        bootRegionService.deleteBootRegion(bootcampId);

        //RequestDto에 있는 부트트랙, 부트언어, 부트지역 다시 재저장
        List<Track> trackList = bootTrackService.save(bootcampRequestDto.toBootTrackList(bootcamp));
        List<Language> languageList = bootLanguageService.save(bootcampRequestDto.toBootLanguageList(bootcamp));
        List<Region> regionList = bootRegionService.save(bootcampRequestDto.toBootRegionList(bootcamp));

        return BootcampResponseDto.of(Optional.ofNullable(bootcamp), Optional.ofNullable(trackList), Optional.ofNullable(languageList), Optional.ofNullable(regionList));
    }

    public Long deleteBootcamp(Long bootcampId) {
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(()->new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        Category category = categoryRepository.findCategoryByBootcamp(bootcamp).get();

        categoryRepository.delete(category);

        bootTrackService.deleteBootTrack(bootcamp.getId());
        bootLanguageService.deleteBootLanguage(bootcamp.getId());
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
            if(bootcamp.getId() == 1L) continue;
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

        //각 부트캠프엔티티마다 지역, 트랙을 찾아 responseDto의 리스트로 만들기
        List<BootcampListResponseDto> bootcampListResponseDtoList = new ArrayList<>();

        for (Bootcamp bootcamp: bootcampList) {
            if(bootcamp.getId() == 1L) continue;
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

        for (Bootcamp bootcamp: bootcampList) {
            if(bootcamp.getId() == 1L) continue;
            Optional<List<Track>> trackList = bootTrackService.getTrackListByBootcampId(bootcamp.getId());
            Optional<List<Region>> regionList = bootRegionService.getRegionListByBootcampId(bootcamp.getId());

            bootcampListResponseDtoList.add(BootcampListResponseDto.of(Optional.of(bootcamp), trackList, regionList ));
        }

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
