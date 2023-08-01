package com.ssafy.campfire.reviewLike.dto.response;

import com.ssafy.campfire.review.domain.Review;

public record ReviewLikeResponse(
        int likes
) {
    public static ReviewLikeResponse from(Review review){
        return new ReviewLikeResponse(
                review.getLikeCnt()
        );
    }
}
