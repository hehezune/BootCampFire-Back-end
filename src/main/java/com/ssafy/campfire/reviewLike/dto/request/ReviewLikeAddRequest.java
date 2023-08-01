package com.ssafy.campfire.reviewLike.dto.request;

import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.reviewLike.domain.ReviewLike;
import com.ssafy.campfire.user.domain.User;

public record ReviewLikeAddRequest(
        User user,
        Review review
) {
    public ReviewLike toReviewLike(){
        return ReviewLike.builder()
                .user(this.user)
                .review(this.review)
                .build();
    }
}

