package com.ssafy.campfire.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class User{

    /*
     * id : pk
     * bootcampId : 소속 부트 캠프 아이디 / 기본 값 = 0 (예비 부트캠퍼)
     * nickname : 사용자가 사용할 별명
     * bojId : 백준 아이디
     * email : 소셜 api를 통해 가입한 이메일
     * createdDate : 가입 날짜
     * updatedDate : 개인 정보 수정 날짜
     * latestAlgoNum : 마지막으로 푼 문제번호
     * role : user 인지 admin 인지 구분
     * */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bootCampId;

    private String nickname;

    private String bojId;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int latestAlgoNum;

    private String role;
}
