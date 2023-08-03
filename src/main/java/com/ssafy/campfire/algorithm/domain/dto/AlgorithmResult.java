package com.ssafy.campfire.algorithm.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@Builder
public record AlgorithmResult (
        String userBojId,
        String result,
        String solveDate

){

}
