package com.ssafy.campfire.algorithm.dto.response;

import com.ssafy.campfire.algorithm.domain.AlgoFiftyRank;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import lombok.Builder;

import java.time.format.DateTimeFormatter;

@Builder
public record AlgoManyRankResponseDto(
        String bootcampName,
        Integer rank,
        Integer algoCnt
) {
    public static AlgoManyRankResponseDto of(Bootcamp bootcamp, Integer rank){
        return AlgoManyRankResponseDto.builder()
                .bootcampName(bootcamp.getName())
                .rank(rank)
                .algoCnt(bootcamp.getAlgoCnt())
                .build();

    }

}
