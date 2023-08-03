package com.ssafy.campfire.algorithm.controller;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.algorithm.domain.AlgorithmResult;
import com.ssafy.campfire.algorithm.dto.request.AlgorithmRequestDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmListResponseDto;
import com.ssafy.campfire.algorithm.dto.response.AlgorithmResponseDto;
import com.ssafy.campfire.algorithm.service.AlgorithmService;
import com.ssafy.campfire.global.login.PrincipalDetails;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @ApiOperation(value ="오늘의 알고리즘 조회")
    @GetMapping
    public BaseResponseDto<AlgorithmResponseDto> getAlgorithm() throws IOException {
        return BaseResponseDto.ok(algorithmService.getAlgorithm());
    }

    @ApiOperation(value ="알고리즘 목록 조회")
    @GetMapping("/lists")
    public BaseResponseDto<List<AlgorithmListResponseDto>> getAlgorithmList() throws IOException {
        return BaseResponseDto.ok(algorithmService.getAlgorithmList());
    }


    @ApiOperation(value ="유저의 알고리즘 풀이 결과 갱신")
    @GetMapping("/{problemId}")
    public BaseResponseDto<List<AlgorithmResult>> getAlgoResult(@PathVariable Long problemId,
                                                                @AuthenticationPrincipal PrincipalDetails user) throws IOException {
        return BaseResponseDto.ok(algorithmService.getAlgorithmResult(user, problemId));
    }

}
