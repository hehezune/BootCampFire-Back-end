package com.ssafy.campfire.bootcamp.controller;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampNameListResponseDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.service.BootLanguageServie;
import com.ssafy.campfire.bootcamp.service.BootRegionService;
import com.ssafy.campfire.bootcamp.service.BootTrackService;
import com.ssafy.campfire.bootcamp.service.BootcampService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/bootcamps")
public class BootcampController {
    private final BootcampService bootcampService;

    private final BootTrackService bootTrackService;
    private final BootLanguageServie bootLanguageServie;
    private final BootRegionService bootRegionService;

    //부트캠프 등록
    @PostMapping
    public BaseResponseDto<BootcampResponseDto> createBootcamp(@RequestBody BootcampRequestDto bootcampRequestDto ){
        return BaseResponseDto.ok(bootcampService.save(bootcampRequestDto));
    }

    @GetMapping("/{bootcampId}")
    public BaseResponseDto<BootcampResponseDto> getBootcamp(@PathVariable Long bootcampId){
        return BaseResponseDto.ok(bootcampService.getBootcamp(bootcampId));
    }


    @PutMapping("/{bootcampId}")
    public BaseResponseDto<BootcampResponseDto> updateBootcamp(@RequestBody BootcampRequestDto bootcampRequestDto, @PathVariable Long bootcampId){
        return BaseResponseDto.ok(bootcampService.updateBootcamp(bootcampId, bootcampRequestDto));
    }


    @DeleteMapping("/{bootcampId}")
    public BaseResponseDto<Long> deleteBootcamp(@PathVariable Long bootcampId){
        return BaseResponseDto.ok(bootcampService.deleteBootcamp(bootcampId));
    }


//    부트캠프 리스트를 이름순으로 보기
    @GetMapping("/lists/names")
    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByName(){
        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByName());
    }


    // 부트캠프 리스트를 평점 순으로 보기
    @GetMapping("/lists/scores")
    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByScore(){
        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByScore());
    }


    //    부트캠프 리스트를 리뷰 개수 순으로 보기
    @GetMapping("/lists/reviews")
    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByReview(){
        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByReview());
    }

    @ApiOperation("지역 목록 가져오기")
    @GetMapping("/regions")
    public BaseResponseDto<List<Region>> getRegionList(){
        return BaseResponseDto.ok(bootRegionService.getRegionList());
    }

    @ApiOperation("트랙명 목록 가져오기")
    @GetMapping("/tracks")
    public BaseResponseDto<List<Track>> getTrackList(){
        return BaseResponseDto.ok(bootTrackService.getTrackList());
    }

    @ApiOperation("언어명 목록 가져오기")
    @GetMapping("/languages")
    public BaseResponseDto<List<Language>> getLanguageList(){
        return BaseResponseDto.ok(bootLanguageServie.getLanguageList());
    }

    @ApiOperation("부트캠프 명으로 검색하기")
    @GetMapping("/search/{bootcampName}")
    public BaseResponseDto<BootcampListResponseDto> searchByBootcampName(@PathVariable String bootcampName){
        return BaseResponseDto.ok(bootcampService.getBootcampByBootcampName(bootcampName));
    }

    @ApiOperation("부트캠프 이름 목록가져오기")
    @GetMapping("/names")
    public BaseResponseDto<List<BootcampNameListResponseDto>> getBootcampNameList(){
        return BaseResponseDto.ok(bootcampService.getBootcampNameList());
    }
}
