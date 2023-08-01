package com.ssafy.campfire.review.controller;


import com.ssafy.campfire.global.login.PrincipalDetails;
import com.ssafy.campfire.review.dto.request.ReviewRequestDto;
import com.ssafy.campfire.review.dto.response.ReviewReponseDto;
import com.ssafy.campfire.review.service.ReviewService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;


    /**
     * @AuthenticationPrincipal 설정 후
     */
//    @PostMapping("/{bootcampId}")
//    public BaseResponseDto<ReviewReponseDto> cerateReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto,
//                                                          @PathVariable Long bootcampId,
//                                                          @AuthenticationPrincipal PrincipalDetails user){
//        return BaseResponseDto.ok(reviewService.save(user.getId(), reviewRequestDto));
//    }

    @PostMapping("/{bootcampId}")
    public BaseResponseDto<ReviewReponseDto> cerateReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto,
                                                          @PathVariable Long bootcampId){
        return BaseResponseDto.ok(reviewService.save(reviewRequestDto.userId(), reviewRequestDto));
    }





}
