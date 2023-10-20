package com.ssafy.campfire.review.controller;

import com.ssafy.campfire.global.oauth2.PrincipalDetails;
import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.review.dto.request.ReviewRequestDto;
import com.ssafy.campfire.review.dto.response.ReviewReponseDto;
import com.ssafy.campfire.review.service.ReviewService;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * @AuthenticationPrincipal 설정 후
     */
    @ApiOperation(value = "리뷰 등록")
    @PostMapping("/{bootcampId}")
    public BaseResponseDto<ReviewReponseDto> cerateReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto,
                                                          @PathVariable Long bootcampId,
                                                          @AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok(reviewService.save(user.getId(), reviewRequestDto));
    }
    @ApiOperation(value = "리뷰 조회")
    @GetMapping("/{bootcampId}/lists")
    public BaseResponseDto<List<ReviewReponseDto>> getReviewList(@PathVariable Long bootcampId,
                                                                 @AuthenticationPrincipal PrincipalDetails user){
        if(user == null){
            return BaseResponseDto.ok(reviewService.getReviewList(bootcampId, null));
        }
        return BaseResponseDto.ok(reviewService.getReviewList(bootcampId, user.getId()));
    }


    @ApiOperation(value = "리뷰 수정")
    @PutMapping("/{bootcampId}/{reviewId}")
    public BaseResponseDto<ReviewReponseDto> updateReview(@PathVariable Long reviewId, @RequestBody @Valid ReviewRequestDto requestDto){
        return BaseResponseDto.ok(reviewService.update(reviewId, requestDto));
    }

    @ApiOperation(value = "리뷰 삭제")
    @DeleteMapping("/{bootcampId}/{reviewId}")
    public BaseResponseDto<Long> deleteReview(@PathVariable Long bootcampId, @PathVariable Long reviewId){
        return BaseResponseDto.ok(reviewService.delete(bootcampId, reviewId));
    }


    @ApiOperation(value = "부트캠프 소속 검사, 작성 이력 검사")
    @GetMapping("/{bootcampId}/vaildation")
    public BaseResponseDto<ReviewReponseDto> validation(@PathVariable Long bootcampId,
                                                        @AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok(reviewService.vaildationCheck(bootcampId, user.getId()));
    }

}
