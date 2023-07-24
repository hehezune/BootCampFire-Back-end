package com.ssafy.campfire.review.domain;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review extends BaseEntity {
    /*
    id :  px
    user : 작성자
    bootcamp : 리뷰할 부트캠프
    tip : 한마디
    good : 좋았던 점
    bad : 안 좋았던 점
    isRecommend : 추천 여부(DB 컬럼명 : is_recommend)
    curriculum : 평가항목_커리큘럼
    potential : 평가항목_성장가능성
    backUp : 평가항목_지원(DB 컬럼명 : back_up)
    management : 평가항목_운영진
    mood : 평가항목_분위기
    score : 평가 점수
    likeCnt : 좋아요 수(DB 컬럼명 : like_cnt)
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

    private String tip;
    private String good;
    private String bad;

    @Column(name = "is_recommend", nullable = false)
    private Boolean isRecommend;


    @Column(name = "like_cnt", nullable = false)
    private Integer likeCnt;


    //-------평가항목
    @Column(nullable = false)
    private Integer curriculum;

    @Column(nullable = false)
    private Integer potential;

    @Column(name = "back_up", nullable = false)
    private Integer backUp;

    @Column(nullable = false )
    private Integer management;

    @Column(nullable = false)
    private Integer mood;

    //총점
    @Column(nullable = false)
    private Double score;

    public Review(String tip, String good, String bad, Boolean isRecommend,
                  Integer curriculum,Integer potential,Integer backUp,Integer management,Integer mood){
        this.tip =tip;
        this.good =good;
        this.bad =bad;
        this.isRecommend =isRecommend;
        this.curriculum =curriculum;
        this.potential =potential;
        this.backUp =backUp;
        this.management =management;
        this.mood =mood;
        this.score = score;
    }

}
