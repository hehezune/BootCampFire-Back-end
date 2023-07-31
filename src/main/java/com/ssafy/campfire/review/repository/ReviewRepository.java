package com.ssafy.campfire.review.repository;

import com.ssafy.campfire.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
