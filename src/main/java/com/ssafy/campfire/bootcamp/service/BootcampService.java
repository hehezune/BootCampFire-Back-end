package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.repository.*;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.campfire.category.domain.enums.CategoryType.BOOTCAMP;

@RequiredArgsConstructor
@Service
@Transactional
public class BootcampService {
    private final BootcampRepository bootcampRepository;
    private  final CategoryRepository categoryRepository;
    private  final BootLanguageRepository bootLanguageRepository;
    private final BootTrackRepository bootTrackRepository;
    private final BootRegionRepository bootRegionRepository;
    private final BootTrackService bootTrackService;
    private final BootLanguageServie bootLanguageServie;
    private final BootRegionService bootRegionService;

    public Bootcamp save(BootcampRequestDto bootcampRequestDto) {
        Bootcamp bootcamp = bootcampRepository.save(bootcampRequestDto.toBootcamp());
        //부트캠프 등록시 카테고리 테이블에도 추가 되도록 하기
        categoryRepository.save(new Category(BOOTCAMP, bootcamp));
        return bootcamp;
    }

    public Optional<Bootcamp> getBootcamp(Long bootcampId){
        return bootcampRepository.findById(bootcampId);
    }

    public Bootcamp updateBootcamp(Long bootcampId, BootcampRequestDto bootcampRequestDto) {
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId).orElseThrow(() -> new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        bootcamp.update(bootcampRequestDto.toBootcamp());

        return bootcamp;
    }

    public Long deleteBootcamp(Long bootcampId) {
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(()->new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));
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
        List<Bootcamp> bootcampList = bootcampRepository.findAllByOrderByReviewCntDesc
                ();

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

    public BootcampResponseDto getBootcampByBootcampName(String bootcampName) {
        Optional<Bootcamp> optionalBootcamp = bootcampRepository.findByName(bootcampName);
        Bootcamp bootcamp = optionalBootcamp.get();
        Optional<List<Track>> trackList = bootTrackRepository.getBootTracksByBootcampId(bootcamp.getId());
        Optional<List<Language>> languageList = bootLanguageRepository.getBootLanguagesByBootcampId(bootcamp.getId());
        Optional<List<Region>> regionList = bootRegionRepository.getBootRegionsByBootcampId(bootcamp.getId());

        return BootcampResponseDto.of(optionalBootcamp, trackList, languageList, regionList);
    }

}
