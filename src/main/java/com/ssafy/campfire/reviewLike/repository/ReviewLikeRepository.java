package com.ssafy.campfire.reviewLike.repository;

import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.reviewLike.domain.ReviewLike;
import com.ssafy.campfire.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long>, CustomReviewLikeRepository {
    Optional<ReviewLike> findByUserAndReview(User user, Review review);

}
