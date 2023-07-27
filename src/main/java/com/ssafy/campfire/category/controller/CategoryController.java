package com.ssafy.campfire.category.controller;

import com.ssafy.campfire.category.dto.response.BoardHotResponse;
import com.ssafy.campfire.category.dto.response.BoardListResponse;
import com.ssafy.campfire.category.dto.response.BoardMainResponse;
import com.ssafy.campfire.category.service.CategoryService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import com.ssafy.campfire.utils.dto.response.GlobalPageResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @ApiOperation(value ="메인화면 : 인기 게시글 10개 보기")
    @GetMapping("/hots")
    public BaseResponseDto<List<BoardHotResponse>> getHotList() {
        return BaseResponseDto.ok(categoryService.getHotList());
    }

    @ApiOperation(value ="메인화면 : 카테고리별 5개씩 - 제목만 보임")
    @GetMapping("/{categoryId}/main")
    public BaseResponseDto<List<BoardMainResponse>> getMainList(@PathVariable Long categoryId) {
        return BaseResponseDto.ok(categoryService.getMainList(categoryId));
    }

    @ApiOperation(value ="메인화면 : 내용 + 제목 검색 결과")
    @GetMapping("/keywords/{keyword}")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getMainSearchTitleContentList(@PathVariable String keyword, Pageable pageable) {
        return BaseResponseDto.ok(categoryService.getMainSearchTitleContentList(keyword, pageable));
    }

    @ApiOperation(value ="메인화면 : 닉네임으로 검색 결과")
    @GetMapping("nickname/{nickname}")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getMainSearchNicknameList(@PathVariable String nickname, Pageable pageable) {
        return BaseResponseDto.ok(categoryService.getMainSearchNicknameList(nickname, pageable));
    }

    /**
     * @AuthenticationPrincipal 설정 후
     */
//    @ApiOperation(value ="카테고리 별 내용 + 제목 검색 결과")
//    @GetMapping("/{categoryId}/keywords/{keyword}")
//    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getSearchTitleContentList(@PathVariable Long categoryId, @PathVariable String keyword, Pageable pageable,
//                                                                                               @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(categoryService.getSearchByTitleContent(user.getId(), categoryId, keyword, pageable));
//    }
//
//    @ApiOperation(value ="카테고리 별 닉네임으로 검색 결과")
//    @GetMapping("/{categoryId}/nickname/{nickname}")
//    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getSearchNicknameList(@PathVariable Long categoryId, @PathVariable String nickname, Pageable pageable,
//                                                                                           @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(categoryService.getSearchByNickname(user.getId(), categoryId, nickname, pageable));
//    }
//
//    @ApiOperation(value ="카테고리 별 게시글 : 최신순")
//    @GetMapping("/{categoryId}")
//    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getNewestList(@PathVariable Long categoryId, Pageable pageable,
//                                                                                   @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(categoryService.getNewestList(user.getId(), categoryId, pageable));
//    }
//
//    @ApiOperation(value ="카테고리 별 게시글 : 추천순")
//    @GetMapping("/{categoryId}/likes")
//    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getLikeOrderList(@PathVariable Long categoryId, Pageable pageable,
//                                                                                      @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(categoryService.getLikeOrderList(user.getId(), categoryId, pageable));
//    }
//
//    @ApiOperation(value ="카테고리 별 게시글 : 조회순")
//    @GetMapping("/{categoryId}/views")
//    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getViewOrderList(@PathVariable Long categoryId, Pageable pageable,
//                                                                                      @AuthenticationPrincipal User user) {
//        return BaseResponseDto.ok(categoryService.getViewOrderList(user.getId(), categoryId, pageable));
//    }

    /**
     * swagger 테스트용
     */
    @ApiOperation(value ="카테고리 별 내용 + 제목 검색 결과")
    @GetMapping("/{categoryId}/keywords/{keyword}")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getSearchTitleContentList(@PathVariable Long categoryId, @PathVariable String keyword, Pageable pageable,
                                                                                               Long userId) {
        return BaseResponseDto.ok(categoryService.getSearchByTitleContent(userId, categoryId, keyword, pageable));
    }

    @ApiOperation(value ="카테고리 별 닉네임으로 검색 결과")
    @GetMapping("/{categoryId}/nickname/{nickname}")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getSearchNicknameList(@PathVariable Long categoryId, @PathVariable String nickname, Pageable pageable,
                                                                                           Long userId) {
        return BaseResponseDto.ok(categoryService.getSearchByNickname(userId, categoryId, nickname, pageable));
    }

    @ApiOperation(value ="카테고리 별 게시글 : 최신순")
    @GetMapping("/{categoryId}")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getNewestList(@PathVariable Long categoryId, Pageable pageable, Long userId) {
        return BaseResponseDto.ok(categoryService.getNewestList(userId, categoryId, pageable));
    }

    @ApiOperation(value ="카테고리 별 게시글 : 추천순")
    @GetMapping("/{categoryId}/likes")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getLikeOrderList(@PathVariable Long categoryId, Pageable pageable, Long userId) {
        return BaseResponseDto.ok(categoryService.getLikeOrderList(userId, categoryId, pageable));
    }

    @ApiOperation(value ="카테고리 별 게시글 : 조회순")
    @GetMapping("/{categoryId}/views")
    public BaseResponseDto<GlobalPageResponseDto<BoardListResponse>> getViewOrderList(@PathVariable Long categoryId, Pageable pageable, Long userId) {
        return BaseResponseDto.ok(categoryService.getViewOrderList(userId, categoryId, pageable));
    }

}
