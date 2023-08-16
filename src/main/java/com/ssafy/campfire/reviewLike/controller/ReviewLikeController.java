package com.ssafy.campfire.reviewLike.controller;


import com.ssafy.campfire.global.oauth2.PrincipalDetails;
import com.ssafy.campfire.reviewLike.dto.response.ReviewLikeResponse;
import com.ssafy.campfire.reviewLike.service.ReviewLikeService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review-likes")
public class ReviewLikeController {
    private final ReviewLikeService reviewLikeService;

    /**
     * @AuthenticationPrincipal 설정 후
     */

    @ApiOperation(value = "리뷰 공감")
    @PostMapping("/{reviewId}")
    public BaseResponseDto<ReviewLikeResponse> likes(@PathVariable Long reviewId,
                                                     @AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok(reviewLikeService.createLike(user.getId(), reviewId));
    }

    @ApiOperation(value = "리뷰 공감 취소")
    @PostMapping("/cancel/{reviewId}")
    public BaseResponseDto<ReviewLikeResponse> cancellikes(@PathVariable Long reviewId,
                                                           @AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok(reviewLikeService.cancelLike(user.getId(), reviewId));
    }

}
