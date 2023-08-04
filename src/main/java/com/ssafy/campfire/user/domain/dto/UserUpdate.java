package com.ssafy.campfire.user.domain.dto;

public record UserUpdate(
        String nickname,
        String imgUrl,
        String bojId
) {
}
