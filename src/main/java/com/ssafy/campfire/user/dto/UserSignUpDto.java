package com.ssafy.campfire.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSignUpDto {

    /*
    * provider : 어떤 소셜 로그인인지 : 카카오, 구글, 네이버
    * email : 소셜의 이메일
    * */

    private String provider;
    private String email;
    private String nickname;
}
