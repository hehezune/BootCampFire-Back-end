package com.ssafy.campfire.bootcamp.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.service.BootcampService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BootcampController {
    private final BootcampService bootcampService;

    //부트캠프 리스트를 이름순으로 보기
    @GetMapping("/bootcamps/names")
    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByName(){
        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByName());
    }
}
