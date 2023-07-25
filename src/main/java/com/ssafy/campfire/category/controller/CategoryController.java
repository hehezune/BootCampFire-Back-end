package com.ssafy.campfire.category.controller;

import com.ssafy.campfire.category.dto.BoardHotResponse;
import com.ssafy.campfire.category.dto.BoardListResponse;
import com.ssafy.campfire.category.dto.BoardMainResponse;
import com.ssafy.campfire.category.service.CategoryService;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import com.ssafy.campfire.utils.dto.response.GlobalPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * 메인화면 용도 : 인기 게시글 목록 보기
     */
    @GetMapping("/hots")
    public BaseResponseDto<List<BoardHotResponse>> getHotList() {
        return BaseResponseDto.ok(categoryService.getHotList());
    }

    /**
     * 메인화면 용도 : 카테고리 별 메인 게시글 목록 보기
     */
    @GetMapping("/{categoryId}/main")
    public BaseResponseDto<List<BoardMainResponse>> getMainList(@PathVariable Long categoryId) {
        return BaseResponseDto.ok(categoryService.getMainList(categoryId));
    }

    /**
     * 카테고리 별 게시글 목록 보기 : 최신순(디폴트)
     */
    @GetMapping("/{categoryId}")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getNewestList(@PathVariable Long categoryId, Pageable pageable, @AuthenticationPrincipal User user) {
        return BaseResponseDto.ok(categoryService.getNewestList(user.getId(), categoryId, pageable));
    }

    /**
     * 카테고리 별 게시글 목록 보기 : 추천순
     */
    @GetMapping("/{categoryId}/likes")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getLikeOrderList(@PathVariable Long categoryId, Pageable pageable, @AuthenticationPrincipal User user) {
        return BaseResponseDto.ok(categoryService.getLikeOrderList(user.getId(), categoryId, pageable));
    }

    /**
     * 카테고리 별 게시글 목록 보기 : 조회순
     */
    @GetMapping("/{categoryId}/views")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getViewOrderList(@PathVariable Long categoryId, Pageable pageable, @AuthenticationPrincipal User user) {
        return BaseResponseDto.ok(categoryService.getViewOrderList(user.getId(), categoryId, pageable));
    }

}
