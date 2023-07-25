package com.ssafy.campfire.user.domain;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.utils.domain.BaseEntity;
import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

    /*
     * id : pk
     * bootcampId : 소속 부트 캠프 아이디 / 기본 값 = 0(미정) (예비 부트캠퍼) /fk
     * nickname : 사용자가 사용할 별명 / 기본 값 = 회원이 사용한 소셜 로그인 사이트의 이메일
     * bojId : 백준 아이디
     * latestAlgoNum : 마지막으로 푼 문제번호
     * role : user 인지 admin 인지 구분
     * kakaoEmail : 카카오 소셜 api를 통해 가입한 이메일
     * googleEmail : 구글 소셜 api를 통해 가입한 이메일
     * naverEmail : 네이버 소셜 api를 통해 가입한 이메일
     * imgUrl : 소속인증 용 사진
     * isPermision : 소속 인증 현황/ true : 인증 허가, false : 인증 미허가
     * refeshToken : refresh 토큰
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

    private String nickname;

    private String bojId;

    private int latestAlgoNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String kakaoEmail;

    @Column(nullable = false)
    private String googleEmail;

    @Column(nullable = false)
    private String naverEmail;

    private String imgUrl;

    @ColumnDefault("false")
    private Boolean isPermision;

    private String refreshToken; // 리프레시 토큰

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    //== 유저 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
