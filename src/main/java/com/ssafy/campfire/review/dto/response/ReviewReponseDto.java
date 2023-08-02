package com.ssafy.campfire.review.dto.response;

import com.ssafy.campfire.review.domain.Review;

public record ReviewReponseDto(
        Long id,
        String user,
        String bootcampName,
        String tip,
        String good,
        String bad,
        Boolean isRecommend,
        Integer likeCnt,
        Integer curriculum,
        Integer potential,
        Integer backUp,
        Integer management,
        Integer mood,
        Double score,
        Boolean isAlreadyReviewLike
) {
    public static ReviewReponseDto of(Review review, Boolean isAlreadyReviewLike){
        return new ReviewReponseDto(
                review.getId(),
                review.getUser().getNickname(),
                review.getBootcamp().getName(),
                review.getTip(),
                review.getGood(),
                review.getBad(),
                review.getIsRecommend(),
                review.getLikeCnt(),
                review.getCurriculum(),
                review.getPotential(),
                review.getBackUp(),
                review.getManagement(),
                review.getMood(),
                review.getScore(),
                isAlreadyReviewLike
        );
    }

    public static ReviewReponseDto nullReview(){
        return new ReviewReponseDto(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
