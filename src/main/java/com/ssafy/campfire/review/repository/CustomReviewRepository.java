package com.ssafy.campfire.review.repository;

import com.ssafy.campfire.review.domain.Review;

import java.util.List;

public interface CustomReviewRepository {
    List<Review> getReviewList(Long bootcampId);
}
