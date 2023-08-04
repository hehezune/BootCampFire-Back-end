package com.ssafy.campfire.user.dto.response;

public record UserReadResponse(
        Long id,
        String nickname,
        String bojId,
        String imgUrl,
        Long bootcampId,
        String bootcampName,
        String email
) {
    public static UserReadResponse from(
            Long id,
            String nickname,
            String bojId,
            String imgUrl,
            Long bootcampId,
            String bootcampName,
            String email
    ){
        return new UserReadResponse(
                id,
                nickname,
                bojId,
                imgUrl,
                bootcampId,
                bootcampName,
                email
        );
    }
}
