package com.ssafy.campfire.comment.controller;

import com.ssafy.campfire.comment.dto.request.CommentCreateRequest;
import com.ssafy.campfire.comment.dto.response.CommentCreateResponse;
import com.ssafy.campfire.comment.service.CommentService;
import com.ssafy.campfire.global.login.PrincipalDetails;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    /**
     * @AuthenticationPrincipal 설정 후
     */
//    @ApiOperation(value ="댓글 저장")
//    @PostMapping
//    public BaseResponseDto<CommentCreateResponse> createBoard(@RequestBody @Valid CommentCreateRequest request,
//                                                              @AuthenticationPrincipal PrincipalDetails user) {
//        return BaseResponseDto.ok(commentService.save(user.getId(), request));
//    }

    /**
     * swagger test
     */
    @ApiOperation(value ="댓글 작성")
    @PostMapping
    public BaseResponseDto<CommentCreateResponse> createBoard(@RequestBody @Valid CommentCreateRequest request,
                                                                             Long userId) {
        return BaseResponseDto.ok(commentService.save(userId, request));
    }
}
