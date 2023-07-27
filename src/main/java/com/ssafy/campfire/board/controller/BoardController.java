package com.ssafy.campfire.board.controller;

import com.ssafy.campfire.board.domain.dto.BoardUpdate;
import com.ssafy.campfire.board.dto.request.BoardCreateRequest;
import com.ssafy.campfire.board.dto.request.BoardUpdateRequest;
import com.ssafy.campfire.board.dto.response.BoardCreateResponse;
import com.ssafy.campfire.board.dto.response.BoardReadResponse;
import com.ssafy.campfire.board.dto.response.BoardUpdateResponse;
import com.ssafy.campfire.board.service.BoardService;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @ApiOperation(value ="게시글 수정")
    @PutMapping("/{boardId}")
    public BaseResponseDto<BoardUpdateResponse> updateBoard(@PathVariable Long boardId,
                                                            @RequestBody @Valid BoardUpdateRequest request) {
        return BaseResponseDto.ok(boardService.update(boardId, request));
    }

    @ApiOperation(value ="게시글 삭제")
    @DeleteMapping("/{boardId}")
    public BaseResponseDto<Long> deletePost(@PathVariable Long boardId) {
        return BaseResponseDto.ok(boardService.delete(boardId));
    }

    /**
     * @AuthenticationPrincipal 설정 후
     */
//    @ApiOperation(value ="게시글 저장")
//    @PostMapping
//    public BaseResponseDto<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest request,
//                                                           @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(boardService.save(user.getId(), request));
//    }
//
//    @ApiOperation(value ="게시글 상세조회")
//    @GetMapping("/{boardId}")
//    public BaseResponseDto<BoardReadResponse> getBoard(@PathVariable Long boardId,
//                                                       @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(boardService.get(boardId, user.getId()));
//    }

    /**
     * swagger test
     */
    @ApiOperation(value ="게시글 저장")
    @PostMapping
    public BaseResponseDto<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest request,
                                                           Long userId) {
        return BaseResponseDto.ok(boardService.save(userId, request));
    }

    @ApiOperation(value ="게시글 상세조회")
    @GetMapping("/{boardId}")
    public BaseResponseDto<BoardReadResponse> getBoard(@PathVariable Long boardId,
                                                       Long userId) {
        return BaseResponseDto.ok(boardService.get(boardId, userId));
    }

}
