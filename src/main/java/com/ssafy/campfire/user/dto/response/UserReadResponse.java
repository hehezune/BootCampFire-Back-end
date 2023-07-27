package com.ssafy.campfire.user.dto.response;

import java.time.LocalDateTime;

public record UserReadResponse(
        Long id,
        String nickname,
        String bojId,
        String imgUrl,
        String email
) {
    public static UserReadResponse from(
            Long id,
            String nickname,
            String bojId,
            String imgUrl,
            String email
    ){
        return new UserReadResponse(
                id,
                nickname,
                bojId,
                imgUrl,
                email
        );
    }
}
