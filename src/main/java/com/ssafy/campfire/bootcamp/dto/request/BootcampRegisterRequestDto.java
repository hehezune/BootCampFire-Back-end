package com.ssafy.campfire.bootcamp.dto.request;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;

import java.time.LocalDateTime;

public record BootcampRegisterRequestDto(
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
        LocalDateTime endDate
) {
    public Bootcamp toBootcamp(){
        return new Bootcamp( name, siteUrl,  process, schedule, description,  cost,card,support,hasCodingtest,on_off,startDate,endDate);
    }
}
