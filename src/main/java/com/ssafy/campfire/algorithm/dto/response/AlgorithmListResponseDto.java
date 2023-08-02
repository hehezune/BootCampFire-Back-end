package com.ssafy.campfire.algorithm.dto.response;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

@Builder
public record AlgorithmListResponseDto(
        Long id,
        Long num,
        String title,
        String date
) {
    public static AlgorithmListResponseDto from(Algorithm algorithm){
        return AlgorithmListResponseDto.builder()
                .id(algorithm.getId())
                .num(algorithm.getNum())
                .title(algorithm.getTitle())
                .date(algorithm.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
