package com.ssafy.campfire.review.repository;

import com.ssafy.campfire.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, CustomReviewRepository {
    Review findByBootcampIdAndUserId(Long bootcampId, Long userId);
}
