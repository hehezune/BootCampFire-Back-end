package com.ssafy.campfire.algorithm.dto.response;

import com.ssafy.campfire.algorithm.domain.AlgoFiftyRank;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import lombok.Builder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
public record AlgoFiftyRankResponseDto(
        String bootcampName,

        Integer rank,
        String createDate
) {
    public static AlgoFiftyRankResponseDto from(AlgoFiftyRank algoFiftyRank){
        return AlgoFiftyRankResponseDto.builder()
                .bootcampName(algoFiftyRank.getBootcamp().getName())
                .rank(algoFiftyRank.getRank())
                .createDate(algoFiftyRank.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss")))
                .build();

    }

}
