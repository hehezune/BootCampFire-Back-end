package com.ssafy.campfire.algorithm.dto.response;

import com.ssafy.campfire.algorithm.domain.AlgorithmResult;
import lombok.Builder;

@Builder
public record AlgorithmResultResponseDto(
        Long userid,
        Long algorithmid,
        Boolean result
) {
    public  static AlgorithmResultResponseDto of(AlgorithmResult algorithmResult, Boolean result){
        return AlgorithmResultResponseDto.builder()
                .userid(algorithmResult.getUserId())
               .algorithmid(algorithmResult.getAlgorithmId())
                .result(result)
                .build();
    }
}
