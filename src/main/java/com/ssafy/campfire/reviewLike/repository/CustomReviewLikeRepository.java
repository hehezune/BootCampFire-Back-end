package com.ssafy.campfire.reviewLike.repository;


public interface CustomReviewLikeRepository {
    Boolean hasLikeByUserId(Long reviewId, Long userId);
    Long deleteByReview(Long reviewId);
}
