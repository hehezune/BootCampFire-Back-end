package com.ssafy.campfire.board.controller;

import com.ssafy.campfire.board.dto.request.BoardCreateRequest;
import com.ssafy.campfire.board.dto.response.BoardCreateResponse;
import com.ssafy.campfire.board.service.BoardService;
import com.ssafy.campfire.user.domain.User;
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
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    /**
     * @AuthenticationPrincipal 설정 후
     */
//    @ApiOperation(value ="게시글 저장")
//    @PostMapping
//    public BaseResponseDto<BoardCreateResponse> createPost(@RequestBody @Valid BoardCreateRequest request,
//                                                           @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(boardService.save(user.getId(), request));
//    }

    /**
     * swagger test
     */
    @ApiOperation(value ="게시글 저장")
    @PostMapping
    public BaseResponseDto<BoardCreateResponse> createPost(@RequestBody @Valid BoardCreateRequest request,
                                                           Long userId) {
        return BaseResponseDto.ok(boardService.save(userId, request));
    }

}
