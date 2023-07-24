package com.ssafy.campfire.category.controller;

import com.ssafy.campfire.category.dto.BoardListResponse;
import com.ssafy.campfire.category.service.CategoryService;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import com.ssafy.campfire.utils.dto.response.GlobalPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getNewestList( Long categoryId,Pageable pageable,
                                                                              @AuthenticationPrincipal User user) {
        return BaseResponseDto.ok(categoryService.getNewestList(user.getId(), categoryId, pageable));
    }


}
