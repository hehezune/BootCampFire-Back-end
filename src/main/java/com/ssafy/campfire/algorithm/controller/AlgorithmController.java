package com.ssafy.campfire.algorithm.controller;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.algorithm.dto.request.AlgorithmRequestDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmResponseDto;
import com.ssafy.campfire.algorithm.service.AlgorithmService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/algorithms")
public class AlgorithmController {
    private final AlgorithmService algorithmService;

    @ApiOperation(value ="알고리즘 등록")
    @PostMapping
    public BaseResponseDto<AlgorithmResponseDto> createAlgorithm(@RequestBody AlgorithmRequestDto algorithmRequestDto) throws IOException {
        return BaseResponseDto.ok(algorithmService.save(algorithmRequestDto));
    }

    @ApiOperation(value ="알고리즘 수정")
    @PutMapping("/{algorithmId}")
    public BaseResponseDto<AlgorithmResponseDto> updateAlgorithm(@RequestBody AlgorithmRequestDto algorithmRequestDto,
                                                              @PathVariable Long algorithmId) throws IOException {
        return BaseResponseDto.ok(algorithmService.updateAlgorithm(algorithmRequestDto, algorithmId));
    }

    @ApiOperation(value ="알고리즘 삭제")
    @DeleteMapping("/{algorithmId}")
    public BaseResponseDto<Long> deleteAlgorithm(@PathVariable Long algorithmId) throws IOException {
        return BaseResponseDto.ok(algorithmService.deleteAlgorithm(algorithmId));
    }

//    @GetMapping("/result/{userId}")
//    public BaseResponseDto<List<Algorithm>> getAlgoResult(@PathVariable Long userId) throws IOException {
//        return BaseResponseDto.ok(algorithmService.getAlgoDatas());
//    }

}
