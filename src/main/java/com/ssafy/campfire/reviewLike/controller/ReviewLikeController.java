package com.ssafy.campfire.reviewLike.controller;


import com.ssafy.campfire.reviewLike.dto.response.ReviewLikeResponse;
import com.ssafy.campfire.reviewLike.service.ReviewLikeService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review-likes")
public class ReviewLikeController {
    private final ReviewLikeService reviewLikeService;

    @PostMapping("/{reviewId}")
    public BaseResponseDto<ReviewLikeResponse> likes(@PathVariable Long reviewId){
        Long userId = 1L;
        return BaseResponseDto.ok(reviewLikeService.createLike(userId, reviewId));
    }

}
