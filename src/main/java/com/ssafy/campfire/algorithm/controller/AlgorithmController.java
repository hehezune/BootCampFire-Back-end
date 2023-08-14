package com.ssafy.campfire.algorithm.controller;

import com.ssafy.campfire.algorithm.domain.dto.AlgorithmResult;
import com.ssafy.campfire.algorithm.dto.request.AlgorithmRequestDto;
import com.ssafy.campfire.algorithm.dto.response.*;
import com.ssafy.campfire.algorithm.service.AlgorithmService;
import com.ssafy.campfire.global.oauth2.PrincipalDetails;

import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public BaseResponseDto<AlgorithmResponseDto> getAlgorithm(@AuthenticationPrincipal PrincipalDetails user) throws IOException {
        return BaseResponseDto.ok(algorithmService.getAlgorithm(user));
    }

    @ApiOperation(value ="알고리즘 목록 조회")
    @GetMapping("/lists")
    public BaseResponseDto<List<AlgorithmListResponseDto>> getAlgorithmList() throws IOException {
        return BaseResponseDto.ok(algorithmService.getAlgorithmList());
    }


    @ApiOperation(value ="유저의 알고리즘 풀이 결과 확인")
    @GetMapping("/{algorithmNum}")
    public BaseResponseDto<AlgorithmResultResponseDto> checkAlgoResult(@PathVariable Long algorithmNum,
                                                                       @AuthenticationPrincipal PrincipalDetails user) throws IOException {
        return BaseResponseDto.ok(algorithmService.checkAlgorithmResult(user, algorithmNum));
    }

    @ApiOperation(value ="50인 달성 순위 조회")
    @GetMapping("/algo-fifty")
    public BaseResponseDto<List<AlgoFiftyRankResponseDto>> getAlgoFiftyRank() {
        return BaseResponseDto.ok(algorithmService.getAlgoFiftyRank());
    }

    @ApiOperation(value ="50인 달성 내 부트캠프 순위 조회")
    @GetMapping("/algo-fifty/my-rank")
    public BaseResponseDto<AlgoFiftyRankResponseDto> getAlgoFiftyMyRank(@AuthenticationPrincipal PrincipalDetails user) {
        return BaseResponseDto.ok(algorithmService.getAlgoFiftyMyRank(user));
    }

    @ApiOperation(value ="알고리즘 많이 푼 부트캠프 순위 조회")
    @GetMapping("/algo-many")
    public BaseResponseDto<List<AlgoManyRankResponseDto>> getAlgoManyRank() {
        return BaseResponseDto.ok(algorithmService.getAlgoManyRank());
    }

    @ApiOperation(value ="많이푼 부트캠프 순웅이 중 내 부트캠프 순위 조회")
    @GetMapping("/algo-many/my-rank")
    public BaseResponseDto<AlgoManyRankResponseDto> getAlgoManyMyRank(@AuthenticationPrincipal PrincipalDetails user) {
        return BaseResponseDto.ok(algorithmService.getAlgoManyMyRank(user));
    }

}
