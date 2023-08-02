package com.ssafy.campfire.algorithm.controller;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.algorithm.service.AlgorithmService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/algorithms")
public class AlgorithmController {
    private final AlgorithmService algorithmService;
    @GetMapping("/result/{userId}")
    public BaseResponseDto<List<Algorithm>> getAlgoResult(@PathVariable Long userId) throws IOException {
        return BaseResponseDto.ok(algorithmService.getAlgoDatas());
    }

}
