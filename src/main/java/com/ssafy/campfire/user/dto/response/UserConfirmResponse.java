package com.ssafy.campfire.user.dto.response;

/*
* 관리자가 소속 인증을 요청한 사용자들의 목록을 보기 위해 반환받는 객체
* 유저 PK, 닉네임, 사진을 반환 하기위함.
* */
public record UserConfirmResponse(
        Long id,
        String nickname,
        String imgUrl
) {
    public static UserConfirmResponse from(
            Long id,
            String nickname,
            String imgUrl
    ){
        return new UserConfirmResponse(
                id,
                nickname,
                imgUrl
        );
    }
}
