package com.ssafy.campfire.algorithm.dto.response;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import lombok.Builder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
public record AlgorithmResponseDto(
        Long id,
        Long num,
        String link,
        String title,
        String description,
        String date
) {
    public static AlgorithmResponseDto from(Algorithm algorithm){
        return AlgorithmResponseDto.builder()
                .id(algorithm.getId())
                .num(algorithm.getNum())
                .link(algorithm.getLink())
                .title(algorithm.getTitle())
                .description(algorithm.getDescription())
                .date(algorithm.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }
}
