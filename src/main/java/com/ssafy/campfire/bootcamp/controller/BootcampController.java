package com.ssafy.campfire.bootcamp.controller;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.service.BootcampService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bootcamps")
public class BootcampController {
    private final BootcampService bootcampService;

    //부트캠프 등록
    @PostMapping
    public BaseResponseDto<BootcampResponseDto> bootcampRegistry(@RequestBody BootcampRegisterRequestDto bootcampRegisterRequestDto ){
        Bootcamp bootcamp = bootcampService.save(bootcampRegisterRequestDto);
        return BaseResponseDto.ok(BootcampResponseDto.of(bootcamp));

    }


    //부트캠프 리스트를 이름순으로 보기
//    @GetMapping("/bootcamps/names")
//    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByName(){
//        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByName());
//    }
}
