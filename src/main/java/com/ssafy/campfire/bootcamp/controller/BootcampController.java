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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
@RestController
@RequiredArgsConstructor
@EnableScheduling
@RequestMapping("/bootcamps")
public class BootcampController {
    private final BootcampService bootcampService;

    private final BootTrackService bootTrackService;
    private final BootLanguageServie bootLanguageServie;
    private final BootRegionService bootRegionService;


    @ApiOperation(value ="부트캠프 등록")
    @PostMapping
    public BaseResponseDto<BootcampResponseDto> createBootcamp(@RequestBody BootcampRequestDto bootcampRequestDto ){
        return BaseResponseDto.ok(bootcampService.save(bootcampRequestDto));
    }

    @ApiOperation(value ="부트캠프 크롤링 후 등록")
    @GetMapping("/getdata")
    public BaseResponseDto<List<BootcampListResponseDto>> saveByCrawling() throws IOException, ParseException {
        return BaseResponseDto.ok(bootcampService.saveByCrawling());
    }

    @ApiOperation(value ="부트캠프 상세조회")
    @GetMapping("/{bootcampId}")
    public BaseResponseDto<BootcampResponseDto> getBootcamp(@PathVariable Long bootcampId){
        return BaseResponseDto.ok(bootcampService.getBootcamp(bootcampId));
    }


    @ApiOperation(value ="부트캠프 수정")
    @PutMapping("/{bootcampId}")
    public BaseResponseDto<BootcampResponseDto> updateBootcamp(@RequestBody BootcampRequestDto bootcampRequestDto, @PathVariable Long bootcampId){
        return BaseResponseDto.ok(bootcampService.updateBootcamp(bootcampId, bootcampRequestDto));
    }


    @ApiOperation(value ="부트캠프 삭제")
    @DeleteMapping("/{bootcampId}")
    public BaseResponseDto<Long> deleteBootcamp(@PathVariable Long bootcampId){
        return BaseResponseDto.ok(bootcampService.deleteBootcamp(bootcampId));
    }


    @ApiOperation(value ="부트캠프 리스트를 이름순으로 조회")
    @GetMapping("/lists/names")
    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByName(){
        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByName());
    }
    @ApiOperation(value ="부트캠프 리스트를 이름순으로 ID와 NAME만 반환 - 관리자페이지 : 소속인증")
    @GetMapping("/lists/onlyNames")
    public BaseResponseDto<List<BootcampNameListResponseDto>> getBootcampIdAndNameListOrderByName(){
        return BaseResponseDto.ok(bootcampService.getBootcampIdAndNameListOrderByName());
    }

    @ApiOperation(value ="부트캠프 리스트를 평점 순으로 조회")
    @GetMapping("/lists/scores")
    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByScore(){
        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByScore());
    }


    @ApiOperation(value ="부트캠프 리뷰 개수 순으로 조회")
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

    @ApiOperation("부트캠프 이름 목록가져오기")
    @GetMapping("/names")
    public BaseResponseDto<List<BootcampNameListResponseDto>> getBootcampNameList(){
        return BaseResponseDto.ok(bootcampService.getBootcampNameList());
    }

    @ApiOperation(value = "매일 자정 부트캠프 algo_cnt 초기화")
    @Scheduled(cron = "0 0 0 * * *")
    public void bootcampAlgoCntInit(){
        bootcampService.bootcampAlgoCntInit();
    }

}
