package com.ssafy.campfire.bootcamp.controller;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.dto.response.BootcampResponseDto;
import com.ssafy.campfire.bootcamp.service.BootLanguageServie;
import com.ssafy.campfire.bootcamp.service.BootRegionService;
import com.ssafy.campfire.bootcamp.service.BootTrackService;
import com.ssafy.campfire.bootcamp.service.BootcampService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public BaseResponseDto<BootcampResponseDto> bootcampRegistry(@RequestBody BootcampRegisterRequestDto bootcampRegisterRequestDto ){
        Bootcamp bootcamp = bootcampService.save(bootcampRegisterRequestDto);

        List<Track> trackList = bootTrackService.save(bootcamp, bootcampRegisterRequestDto);
        List<Language> languageList = bootLanguageServie.save(bootcamp, bootcampRegisterRequestDto);
        List<Region> regionList = bootRegionService.save(bootcamp, bootcampRegisterRequestDto);

        return BaseResponseDto.ok(BootcampResponseDto.of(Optional.ofNullable(bootcamp), trackList,languageList, regionList));
    }

    @GetMapping("/{bootcampId}")
    public BaseResponseDto<BootcampResponseDto> getBootcamp(@PathVariable Long bootcampId){
        Optional<Bootcamp> bootcamp = bootcampService.getBootcamp(bootcampId);

        List<Track> trackList = bootTrackService.getTrackListByBootcamp(bootcampId);
//        List<Track> trackList = bootTrackService.getTrackListByBootcamp(bootcamp);

        System.out.println("트랙 트랙");
        System.out.println(trackList.size());
        for ( Track t: trackList) {
            System.out.println("트랙:" +t.toString());

        }
        List<Language> languageList = bootLanguageServie.getLanguageListByBootcamp(bootcamp);
        List<Region> regionList = bootRegionService.getRegionListByBootcamp(bootcamp);

        return BaseResponseDto.ok(BootcampResponseDto.of(bootcamp, trackList,languageList, regionList));
    }



    //부트캠프 리스트를 이름순으로 보기
//    @GetMapping("/bootcamps/names")
//    public BaseResponseDto<List<BootcampListResponseDto>> getBootcampListOrderByName(){
//        return BaseResponseDto.ok(bootcampService.getBootcampListOrderByName());
//    }
}
