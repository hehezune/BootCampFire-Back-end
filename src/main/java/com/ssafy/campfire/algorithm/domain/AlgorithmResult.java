package com.ssafy.campfire.algorithm.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class AlgorithmResult {
    private Long userId;
    private Long algorithmId;
    private String problemId;
    private String result;
    private String solveDate;

}
