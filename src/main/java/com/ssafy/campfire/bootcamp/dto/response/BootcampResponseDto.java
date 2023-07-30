package com.ssafy.campfire.bootcamp.dto.response;


import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Language;
import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.domain.Track;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Builder
public record BootcampResponseDto(
        Long id,
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
        Integer algoCnt,
        List<Track> tracks,
        List<Language> languages,
        List<Region> regions) {
//    public static BootcampResponseDto of(Bootcamp bootcamp, List<Track> tracks, List<Language> languages, List<Region> regions) {
//
//        return BootcampResponseDto.builder()
//                .id(bootcamp.getId())
//                .name(bootcamp.getName())
//                .siteUrl(bootcamp.getSiteUrl())
//                .process(bootcamp.getProcess())
//                .schedule(bootcamp.getSchedule())
//                .description(bootcamp.getDescription())
//                .cost(bootcamp.getCost())
//                .card(bootcamp.getCard())
//                .support(bootcamp.getSupport())
//                .hasCodingtest(bootcamp.getHasCodingtest())
//                .on_off(bootcamp.getOn_off())
//                .startDate(bootcamp.getStartDate())
//                .endDate(bootcamp.getEndDate())
//                .reviewCnt(bootcamp.getReviewCnt())
//                .score(bootcamp.getTotalScore() / (double) bootcamp.getReviewCnt())
//                .tracks(tracks)
//                .languages(languages)
//                .regions(regions)
//                .build();
//    }

    public static BootcampResponseDto of(Optional<Bootcamp> bootcamp, List<Track> tracks, List<Language> languages,List<Region> regions) {
        return BootcampResponseDto.builder()
                .id(bootcamp.get().getId())
                .name(bootcamp.get().getName())
                .siteUrl(bootcamp.get().getSiteUrl())
                .process(bootcamp.get().getProcess())
                .schedule(bootcamp.get().getSchedule())
                .description(bootcamp.get().getDescription())
                .cost(bootcamp.get().getCost())
                .card(bootcamp.get().getCard())
                .support(bootcamp.get().getSupport())
                .hasCodingtest(bootcamp.get().getHasCodingtest())
                .on_off(bootcamp.get().getOn_off())
                .startDate(bootcamp.get().getStartDate())
                .endDate(bootcamp.get().getEndDate())
                .reviewCnt(bootcamp.get().getReviewCnt())
                .score(bootcamp.get().getTotalScore() / (double) bootcamp.get().getReviewCnt())
                .tracks(tracks)
                .languages(languages)
                .regions(regions)
                .build();
    }
}
