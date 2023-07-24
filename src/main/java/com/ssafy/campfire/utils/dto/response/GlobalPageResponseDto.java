package com.ssafy.campfire.utils.dto.response;

import java.util.List;
import org.springframework.data.domain.Page;

public record GlobalPageResponseDto<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int pageNumber,
        int numberOfElements,
        boolean hasNextPages
) {

    public static <T> GlobalPageResponseDto<T> of(Page<T> page) {
        return new GlobalPageResponseDto<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getNumberOfElements(),
                page.hasNext()
        );
    }
}
