package com.ssafy.campfire.comment.controller;

import com.ssafy.campfire.comment.dto.request.CommentCreateRequest;
import com.ssafy.campfire.comment.dto.request.CommentUpdateRequest;
import com.ssafy.campfire.comment.dto.response.CommentCreateResponse;
import com.ssafy.campfire.comment.dto.response.CommentReadResponse;
import com.ssafy.campfire.comment.dto.response.CommentUpdateResponse;
import com.ssafy.campfire.comment.service.CommentService;
import com.ssafy.campfire.global.oauth2.PrincipalDetails;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value ="댓글 수정")
    @PutMapping("/{commentId}")
    public BaseResponseDto<CommentUpdateResponse> updateComment(@PathVariable Long commentId,
                                                                @RequestBody @Valid CommentUpdateRequest request) {
        return BaseResponseDto.ok(commentService.update(commentId, request));
    }

    @ApiOperation(value ="댓글 조회")
    @GetMapping("/list/{boardId}")
    public BaseResponseDto<List<CommentReadResponse>> getMainList(@PathVariable Long boardId,
                                                                  @AuthenticationPrincipal PrincipalDetails user) {
        if(user==null){
            return BaseResponseDto.ok(commentService.getCommentList(boardId, null));
        }

        return BaseResponseDto.ok(commentService.getCommentList(boardId, user.getId()));
    }

    @ApiOperation(value ="댓글 삭제")
    @DeleteMapping("/{commentId}")
    public BaseResponseDto<Long> deleteComment(@PathVariable Long commentId) {
        return BaseResponseDto.ok(commentService.delete(commentId));
    }

    /**
     * @AuthenticationPrincipal 설정 후
     */
    @ApiOperation(value ="댓글 작성")
    @PostMapping
    public BaseResponseDto<CommentCreateResponse> createBoard(@RequestBody @Valid CommentCreateRequest request,
                                                              @AuthenticationPrincipal PrincipalDetails user) {
        return BaseResponseDto.ok(commentService.save(user.getId(), request));
    }

    /**
     * swagger test
     */
//    @ApiOperation(value ="댓글 작성")
//    @PostMapping
//    public BaseResponseDto<CommentCreateResponse> createBoard(@RequestBody @Valid CommentCreateRequest request,
//                                                              Long userId) {
//        return BaseResponseDto.ok(commentService.save(userId, request));
//    }
}
