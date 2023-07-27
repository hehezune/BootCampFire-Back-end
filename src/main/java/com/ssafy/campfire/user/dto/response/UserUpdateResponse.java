package com.ssafy.campfire.user.dto.response;

public record UserUpdateResponse(
        Long id,
        String nickname,
        String bojId,
        String bootcamp,
        String imgUrl,
        String email
) {
}
