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

import javax.transaction.Transactional;

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
}
