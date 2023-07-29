package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.BootTrack;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Track;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.repository.BootTrackRepository;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.bootcamp.repository.TrackRepository;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BootcampService {
    private final BootcampRepository bootcampRepository;
    private  final CategoryRepository categoryRepository;

    public Bootcamp save(BootcampRegisterRequestDto bootcampRegisterRequestDto) {
        Bootcamp bootcamp = bootcampRepository.save(bootcampRegisterRequestDto.toBootcamp());
        //부트캠프 등록시 카테고리 테이블에도 추가 되도록 하기
//        categoryRepository.save(new Category("BOOTCAMP", bootcamp.getId()));
        return bootcamp;
    }


//    @Transactional //트랜잭션 범위는 유지하되 기능을 조회로 제한함으로써 조회 속도가 개선
//    public List<BootcampListResponseDto> getBootcampListOrderByName() {
//        List<Bootcamp> bootcampList = bootcampRepository.findAllByOrderByName();
//
//        List<BootTrack> trackId = bootTrackRepository.findBootTracksByBootcamp_Id((long)5);
//
//
//
////        List<BootcampListResponseDto> bootcampListResponseDtos = (List<BootcampListResponseDto>) bootcampRepository
////                .findAllByOrderByName()
////                .stream()
////                .map(BootcampListResponseDto::new);
//        return bootcampListResponseDtos;
//    }


}
