package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BootcampService {
    private final BootcampRepository bootcampRepository;

    @Transactional(readOnly = true) //트랜잭션 범위는 유지하되 기능을 조회로 제한함으로써 조회 속도가 개선
    public List<BootcampListResponseDto> getBootcampListOrderByName() {
        List<BootcampListResponseDto> bootcampListResponseDtos = (List<BootcampListResponseDto>) bootcampRepository
                .findAllByOrderByName()
                .stream()
                .map(BootcampListResponseDto::new);
        return bootcampListResponseDtos;
    }


}
S