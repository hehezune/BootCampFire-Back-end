package com.ssafy.campfire.review.service;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.review.dto.request.ReviewRequestDto;
import com.ssafy.campfire.review.dto.response.ReviewReponseDto;
import com.ssafy.campfire.review.repository.ReviewRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BootcampRepository bootcampRepository;


    public ReviewReponseDto save(Long userId, ReviewRequestDto reviewRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Bootcamp bootcamp = bootcampRepository.findById(reviewRequestDto.bootcampId())
                .orElseThrow(()->new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));

        Review review = reviewRequestDto.toReview();
        review.setBootcamp(bootcamp);
        review.writeBy(user);

        Review saveReview;
        saveReview = reviewRepository.save(review);

        bootcamp.updateTotalScore(saveReview.getScore());


        return ReviewReponseDto.of(saveReview);
    }

    @Transactional(readOnly = true)
    public List<ReviewReponseDto> getReviewList(Long bootcampId){
        List<ReviewReponseDto> reviewResponses = (List<ReviewReponseDto>) reviewRepository
                .getReviewList(bootcampId)
                .stream()
                .map(ReviewReponseDto::of).toList();
        return reviewResponses;
    }

    public ReviewReponseDto update(Long reviewId, ReviewRequestDto request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));

        review.update(request.toDo());

        return ReviewReponseDto.of(review);
    }

    public Long delete(Long bootcampId, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));

        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));
        bootcamp.minusReviewCnt();

        reviewRepository.delete(review);
        return review.getId();
    }

    public boolean bootcampCheck(Long bootcampId, Long userId){
        User user = userRepository.findUserById(userId);

        if(user.getBootcamp().getId() != bootcampId){
            return false;
        }

        return true;
    }

    public boolean historyCheck(Long bootcampId, Long userId){
        Review review = reviewRepository.findByBootcampIdAndUserId(bootcampId, userId);

        if (review != null){
            return false;
        }

        return true;
    }

    public boolean vaildationCheck(Long bootcampId, Long userId){
        if(!bootcampCheck(bootcampId, userId)){
            return false;
        }

        if (!historyCheck(bootcampId,userId)){
            return false;
        }

        return true;
    }
}
