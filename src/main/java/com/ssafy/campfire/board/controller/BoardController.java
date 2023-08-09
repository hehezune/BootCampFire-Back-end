package com.ssafy.campfire.board.controller;

import com.ssafy.campfire.board.domain.dto.BoardUpdate;
import com.ssafy.campfire.board.dto.request.BoardCreateRequest;
import com.ssafy.campfire.board.dto.request.BoardUpdateRequest;
import com.ssafy.campfire.board.dto.response.BoardCreateResponse;
import com.ssafy.campfire.board.dto.response.BoardReadResponse;
import com.ssafy.campfire.board.dto.response.BoardUpdateResponse;
import com.ssafy.campfire.board.dto.response.UserBoardListResponse;
import com.ssafy.campfire.board.service.BoardService;
import com.ssafy.campfire.global.oauth2.PrincipalDetails;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import com.ssafy.campfire.utils.dto.response.GlobalPageResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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
    @ApiOperation(value ="게시글 저장")
    @PostMapping
    public BaseResponseDto<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest request,
                                                           @AuthenticationPrincipal PrincipalDetails user) {
        return BaseResponseDto.ok(boardService.save(user.getId(), request));
    }

    @ApiOperation(value ="게시글 상세조회")
    @GetMapping("/{boardId}")
    public BaseResponseDto<BoardReadResponse> getBoard(@PathVariable Long boardId,
                                                       @AuthenticationPrincipal PrincipalDetails user) {
        if(user==null){
            return BaseResponseDto.ok(boardService.getBoard(boardId, null));
        }
        return BaseResponseDto.ok(boardService.getBoard(boardId, user.getId()));
    }

    /**
     * slice
     */
    @ApiOperation(value ="사용자 게시글 조회")
    @GetMapping("/users")
    public BaseResponseDto<Slice<UserBoardListResponse>> getUserBoard(Pageable pageable,
                                                                      @AuthenticationPrincipal PrincipalDetails user) {
        return BaseResponseDto.ok(boardService.getUserBoard(user.getId(), pageable));
    }

    /**
     * page
     */
//    @ApiOperation(value ="사용자 게시글 조회")
//    @GetMapping("/users")
//    public BaseResponseDto<GlobalPageResponseDto<UserBoardListResponse>> getUserBoard(Pageable pageable,
//                                                                                  @AuthenticationPrincipal PrincipalDetails user) {
//        return BaseResponseDto.ok(boardService.getUserBoard(user.getId(), pageable));
//    }

    /**
     * swagger test
     */
//    @ApiOperation(value ="게시글 저장")
//    @PostMapping
//    public BaseResponseDto<BoardCreateResponse> createBoard(@RequestBody @Valid BoardCreateRequest request,
//                                                           Long userId) {
//        return BaseResponseDto.ok(boardService.save(userId, request));
//    }
//
//    @ApiOperation(value ="게시글 상세조회")
//    @GetMapping("/{boardId}")
//    public BaseResponseDto<BoardReadResponse> getBoard(@PathVariable Long boardId,
//                                                       Long userId) {
//        return BaseResponseDto.ok(boardService.getBoard(boardId, userId));
//    }
//
//    @ApiOperation(value ="사용자 게시글 조회")
//    @GetMapping("/users")
//    public BaseResponseDto<GlobalPageResponseDto<UserBoardListResponse>> getNewestList(Pageable pageable, Long userId) {
//        return BaseResponseDto.ok(boardService.getUserBoard(userId, pageable));
//    }

}
