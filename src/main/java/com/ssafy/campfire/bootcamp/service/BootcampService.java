package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BootcampService {
    private final BootcampRepository bootcampRepository;
    private  final CategoryRepository categoryRepository;

    public Bootcamp save(BootcampRequestDto bootcampRequestDto) {
        Bootcamp bootcamp = bootcampRepository.save(bootcampRequestDto.toBootcamp());
        //부트캠프 등록시 카테고리 테이블에도 추가 되도록 하기
//        categoryRepository.save(new Category("BOOTCAMP", bootcamp.getId()));
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
