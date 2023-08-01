package com.ssafy.campfire.review.dto.request;

import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.review.domain.dto.ReviewUpdate;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

import javax.validation.constraints.NotNull;

public record ReviewRequestDto(
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
        @NotNull(message = "작성자는 필수입니다.")
        Long userId,
        @NotNull(message = "리뷰할 부트캠프는 필수입니다.")
        Long bootcampId,
        String tip,
        String good,
        String bad,
        Boolean isRecommend,
        @NotNull(message = "커리큘럼에 대한 만족도는 필수입니다.")
        Integer curriculum,
        @NotNull(message = "성장가능성에 대한 만족도는 필수입니다.")
        Integer potential,
        @NotNull(message = "지원에 대한 만족도는 필수입니다.")
        Integer backUp,
        @NotNull(message = "운영진에 대한 만족도는 필수입니다.")
        Integer management,
        @NotNull(message = "학습분위기에 대한 만족도는 필수입니다.")
        Integer mood
) {
    public Review toReview(){
        return new Review(tip,good, bad, isRecommend, curriculum, potential,backUp, management, mood);
    }
    public ReviewUpdate toDo(){
        return new ReviewUpdate(this.tip,this.good, this.bad, this.isRecommend, this.curriculum, this.potential,this.backUp, this.management, this.mood);
    }
}
