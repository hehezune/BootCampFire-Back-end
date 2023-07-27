package com.ssafy.campfire.bootcamp.dto.response;


import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BootcampResponseDto(Long id,
                                  String name,
                                  String siteUrl,
                                  String process,
                                  String schedule,
                                  String description,
                                  Double cost,
                                  Boolean card,
                                  Boolean support,
                                  Boolean hasCodingtest,
                                  String on_off,
                                  LocalDateTime startDate,
                                  LocalDateTime endDate,
                                  Integer reviewCnt,
                                  Double score,
                                  Integer algoCnt) {
    public  static BootcampResponseDto of(Bootcamp bootcamp){
        return BootcampResponseDto.builder()
                .id(bootcamp.getId())
                .name(bootcamp.getName())
                .siteUrl(bootcamp.getSiteUrl())
                .process(bootcamp.getProcess())
                .schedule(bootcamp.getSchedule())
                .description(bootcamp.getDescription())
                .cost(bootcamp.getCost())
                .card(bootcamp.getCard())
                .support(bootcamp.getSupport())
                .hasCodingtest(bootcamp.getHasCodingtest())
                .on_off(bootcamp.getOn_off())
                .startDate(bootcamp.getStartDate())
                .endDate(bootcamp.getEndDate())
                .reviewCnt(bootcamp.getReviewCnt())
                .score(bootcamp.getTotalScore()/(double)bootcamp.getReviewCnt())
                .build();
    }
}
