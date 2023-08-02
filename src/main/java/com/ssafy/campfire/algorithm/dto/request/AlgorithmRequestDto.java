package com.ssafy.campfire.algorithm.dto.request;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.algorithm.service.AlgorithmService;

import java.time.LocalDate;

public record AlgorithmRequestDto(
        Long num,
        LocalDate date
) {
    public Algorithm toAlgorithm(){
        return  new Algorithm(num, date);
    }

}
