package com.ssafy.campfire.review.service;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.review.dto.request.ReviewRequestDto;
import com.ssafy.campfire.review.dto.response.ReviewReponseDto;
import com.ssafy.campfire.review.repository.ReviewRepository;
import com.ssafy.campfire.reviewLike.domain.ReviewLike;
import com.ssafy.campfire.reviewLike.dto.response.ReviewLikeResponse;
import com.ssafy.campfire.reviewLike.repository.ReviewLikeRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ErrorManager;

@RequiredArgsConstructor
@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BootcampRepository bootcampRepository;
    private final ReviewLikeRepository reviewLikeRepository;


    public ReviewReponseDto save(Long userId, ReviewRequestDto reviewRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Bootcamp bootcamp = bootcampRepository.findById(reviewRequestDto.bootcampId())
                .orElseThrow(()->new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        if(user.getBootcamp().getId() != bootcamp.getId()){
            throw new BusinessException(ErrorMessage.INVALID_REVIEW_REQUEST);
        }

        Review review = reviewRequestDto.toReview();
        review.setBootcamp(bootcamp);
        review.writeBy(user);

        Review saveReview;
        saveReview = reviewRepository.save(review);

        bootcamp.addTotalScore(saveReview.getScore());


        return ReviewReponseDto.of(saveReview, false);
    }

    @Transactional(readOnly = true)
    public List<ReviewReponseDto> getReviewList(Long bootcampId, Long userId){
        List<ReviewReponseDto> reviewResponses = null;
        if(userId == null){
            reviewResponses = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                reviewResponses.add(ReviewReponseDto.nullReview());
            }
        }else{
            User user = userRepository.findById(userId)
                    .orElseThrow(()->new BusinessException(ErrorMessage.USER_NOT_FOUND));
            reviewResponses = (List<ReviewReponseDto>) reviewRepository
                    .getReviewList(bootcampId)
                    .stream()
                    .map((Review review) -> ReviewReponseDto.of(review, isAlreadyReviewLike(review, user))).toList();
        }
        return reviewResponses;
    }


    private Boolean isAlreadyReviewLike(Review review, User user) {
        return reviewLikeRepository.findByUserAndReview(user, review).isPresent();
    }

    public ReviewReponseDto update(Long reviewId, ReviewRequestDto request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));
        Bootcamp bootcamp = bootcampRepository.findById(request.bootcampId())
                .orElseThrow(()->new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        bootcamp.subTotalScore(review.getScore());

        review.update(request.toDo());

        bootcamp.addTotalScore(review.getScore());

        return ReviewReponseDto.of(review, isAlreadyReviewLike(review, review.getUser()));
    }

    public Long delete(Long bootcampId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));

        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));

        bootcamp.subTotalScore(review.getScore());
        reviewLikeRepository.deleteByReview(reviewId);
        reviewRepository.delete(review);
        return review.getId();
    }

    public ReviewReponseDto vaildationCheck(Long bootcampId, Long userId){
        Review review = reviewRepository.findByBootcampIdAndUserId(bootcampId, userId);
        if(review == null){
            return ReviewReponseDto.nullReview();
        }
        return ReviewReponseDto.of(review, isAlreadyReviewLike(review, review.getUser()));
    }
}
