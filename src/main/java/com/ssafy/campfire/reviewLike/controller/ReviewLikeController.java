package com.ssafy.campfire.reviewLike.controller;


import com.ssafy.campfire.reviewLike.dto.response.ReviewLikeResponse;
import com.ssafy.campfire.reviewLike.service.ReviewLikeService;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review-likes")
public class ReviewLikeController {
    private final ReviewLikeService reviewLikeService;

    /**
     * @AuthenticationPrincipal 설정 후
     */
//    @PostMapping("/{reviewId}")
//    public BaseResponseDto<ReviewLikeResponse> likes(@PathVariable Long reviewId,
//                                                     @AuthenticationPrincipal User user){
//        return BaseResponseDto.ok(reviewLikeService.createLike(user.getId(), reviewId));
//    }
//
//    @PostMapping("/cancel/{reviewId}")
//    public BaseResponseDto<ReviewLikeResponse> cancellikes(@PathVariable Long reviewId,
//                                                           @AuthenticationPrincipal User user){
//        return BaseResponseDto.ok(reviewLikeService.cancelLike(user.getId(), reviewId));
//    }

    @PostMapping("/{reviewId}")
    public BaseResponseDto<ReviewLikeResponse> likes(@PathVariable Long reviewId){
        Long userId = 1L;
        return BaseResponseDto.ok(reviewLikeService.createLike(userId, reviewId));
    }

    @PostMapping("/cancel/{reviewId}")
    public BaseResponseDto<ReviewLikeResponse> cancellikes(@PathVariable Long reviewId){
        Long userId = 1L;
        return BaseResponseDto.ok(reviewLikeService.cancelLike(userId, reviewId));
    }

}
