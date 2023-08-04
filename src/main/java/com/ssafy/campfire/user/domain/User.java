package com.ssafy.campfire.user.domain;

import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.QBootcamp;
import com.ssafy.campfire.user.domain.dto.UserUpdate;
import com.ssafy.campfire.utils.domain.BaseEntity;
import javax.persistence.*;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@ToString
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
     * email : 소셜 api를 통해 가입한 이메일
     * provider : 가입 한 소셜 경로
     * imgUrl : 소속인증 용 사진
     * isPermision : 소속 인증 현황/ true : 인증 허가, false : 인증 미허가
     * refeshToken : refresh 토큰
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

    private String nickname;

    private String bojId;

    private Long latestAlgoNum;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

    private String provider;

    private String imgUrl;

    @ColumnDefault("true")
    private Boolean isPermision;

    private String refreshToken; // 리프레시 토큰

    public User(String nickname, String email, String provider, Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
        this.role = Role.USER;
        this.isPermision = true;
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    public User(Long id, Bootcamp bootcamp, String nickname, int latestAlgoNum, Role role, String email, String provider, String imgUrl, Boolean isPermision, String refreshToken) {
        super();
    }


    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    public void updatedUpdatedate(){this.updatedDate = LocalDateTime.now();}

    //== 유저 필드 업데이트 ==//
    public void updateNickname(String updateNickname) {
        this.nickname = updateNickname;
        updatedUpdatedate();
    }
    public void updateBootcamp(Bootcamp bootcamp) {
        this.bootcamp = bootcamp;
        updatedUpdatedate();
    }

    public void update(UserUpdate userUpdate){
        this.nickname = userUpdate.nickname();
        this.imgUrl = userUpdate.imgUrl();
        this.bojId = userUpdate.bojId();
        updatedUpdatedate();
    }

    public void updatePermision(boolean isPermision){
        this.isPermision = isPermision;
        updatedUpdatedate();
    }

    public void updateLatestAlgoNum(Long algorithmNum){
        this.latestAlgoNum = algorithmNum;
        updatedUpdatedate();
    }
}
