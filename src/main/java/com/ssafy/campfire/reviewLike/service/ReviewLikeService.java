package com.ssafy.campfire.reviewLike.service;

import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.review.repository.ReviewRepository;
import com.ssafy.campfire.reviewLike.dto.request.ReviewLikeAddRequest;
import com.ssafy.campfire.reviewLike.dto.response.ReviewLikeResponse;
import com.ssafy.campfire.reviewLike.repository.ReviewLikeRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewLikeService {
    private final ReviewLikeRepository reviewLikeRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;


    public ReviewLikeResponse createLike(Long userId, Long reviewId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.REVIEW_NOT_FOUND));

        boolean isAlreadyLike = reviewLikeRepository.findByUserAndReview(user, review).isPresent();

        if(!isAlreadyLike){
            ReviewLikeAddRequest reviewLikeAddRequest = new ReviewLikeAddRequest(user, review);
            reviewLikeRepository.save(reviewLikeAddRequest.toReviewLike());
            review.addLikeCnt();
        }

        return ReviewLikeResponse.from(review);
    }
}
