package com.ssafy.campfire.review.domain.dto;

public record ReviewUpdate(
        String tip,
        String good,
        String bad,
        Boolean isRecommend,
        Integer curriculum,
        Integer potential,
        Integer backUp,
        Integer management,
        Integer mood
) {
}
