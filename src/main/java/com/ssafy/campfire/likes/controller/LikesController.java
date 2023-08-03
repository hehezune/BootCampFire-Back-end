package com.ssafy.campfire.likes.controller;

import com.ssafy.campfire.likes.dto.response.LikesResponse;
import com.ssafy.campfire.likes.service.LikesService;
import com.ssafy.campfire.user.domain.User;
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
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    /**
     * @AuthenticationPrincipal 설정 후
     */
    @ApiOperation(value ="좋아요")
    @PostMapping("/{boardId}")
    public BaseResponseDto<LikesResponse> likes(@PathVariable Long boardId,
                                                @AuthenticationPrincipal User user) {
        return BaseResponseDto.ok(likesService.createLikes(user.getId(), boardId));
    }

    @ApiOperation(value ="좋아요 취소")
    @PostMapping("/cancle/{boardId}")
    public BaseResponseDto<LikesResponse> cancelLikes(@PathVariable Long boardId,
                                                      @AuthenticationPrincipal User user) {
        return BaseResponseDto.ok(likesService.cancelLikes(user.getId(), boardId));
    }

    /**
     * swagger test
     */
//    @ApiOperation(value ="좋아요")
//    @PostMapping("/{boardId}")
//    public BaseResponseDto<LikesResponse> likes(@PathVariable Long boardId,
//                                                Long userId) {
//        return BaseResponseDto.ok(likesService.createLikes(userId, boardId));
//    }
//
//    @ApiOperation(value ="좋아요취소")
//    @PostMapping("/cancle/{boardId}")
//    public BaseResponseDto<LikesResponse> cancelLikes(@PathVariable Long boardId,
//                                                      Long userId) {
//        return BaseResponseDto.ok(likesService.cancelLikes(userId, boardId));
//    }

}
