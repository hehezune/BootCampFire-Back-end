package com.ssafy.campfire.algorithm.dto.response;

import com.ssafy.campfire.algorithm.domain.dto.AlgorithmResult;
import lombok.Builder;

@Builder
public record AlgorithmResultResponseDto(
        Long userId,
        Long algorithmId,
        Boolean result
) {
    public  static AlgorithmResultResponseDto of(Long userId, Long algorithmId, Boolean result){
        return AlgorithmResultResponseDto.builder()
                .userId(userId)
                .algorithmId(algorithmId)
                .result(result)
                .build();
    }
}
