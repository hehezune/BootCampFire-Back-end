package com.ssafy.campfire.user.dto.response;

import com.ssafy.campfire.user.domain.Role;

import java.time.LocalDateTime;
public record UserReadResponse(
        Long id,
        String nickname,
        String bojId,
        String imgUrl,
        Long bootcampId,
        String bootcampName,
        String email,

        Role role
) {
    public static UserReadResponse from(
            Long id,
            String nickname,
            String bojId,
            String imgUrl,
            Long bootcampId,
            String bootcampName,
            String email,
            Role role
    ){
        return new UserReadResponse(
                id,
                nickname,
                bojId,
                imgUrl,
                bootcampId,
                bootcampName,
                email,
                role
        );
    }
}
