package com.ssafy.campfire.bootcamp.dto.response;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.domain.Track;
import lombok.Builder;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Builder
public record BootcampNameListResponseDto(
        Long id,
        String name
) {
    public static BootcampNameListResponseDto of(Optional<Bootcamp> bootcamp) {
    return BootcampNameListResponseDto.builder()
            .id(bootcamp.get().getId())
            .name(bootcamp.get().getName())
            .build();
    }
}

