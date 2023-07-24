package com.ssafy.campfire.user.domain;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.utils.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {

    /*
     * id : pk
     * bootcampId : 소속 부트 캠프 아이디 / 기본 값 = 0(미정) (예비 부트캠퍼) /fk
     * nickname : 사용자가 사용할 별명
     * bojId : 백준 아이디
     * email : 소셜 api를 통해 가입한 이메일
     * latestAlgoNum : 마지막으로 푼 문제번호
     * role : user 인지 admin 인지 구분
     * imgUrl : 소속인증 용 사진
     * isPermision : 소속 인증 현황/ true : 인증 허가, false : 인증 미허가
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    @Column(nullable = false) // 처음에 예비 부트캠프 소속으로
    private Bootcamp bootcamp;

    private String nickname;

    private String bojId;

    @Column(nullable = false)
    private String email;

    private int latestAlgoNum;

    @Column(nullable = false)
    private String role;

    private String imgUrl;

    @ColumnDefault("false")
    private Boolean isPermision;
}
