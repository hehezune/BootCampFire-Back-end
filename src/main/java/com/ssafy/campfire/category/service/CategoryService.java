package com.ssafy.campfire.category.service;

import com.ssafy.campfire.category.dto.BoardListResponse;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.ssafy.campfire.utils.dto.response.GlobalPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getNewestList(Long userId, Long categoryId, Pageable pageable){
        // user 찾아와야 함
        Page<BoardListResponse> page = categoryRepository
                .getBoardByNewest(categoryId, pageable)
                .map(BoardListResponse::of);
        return GlobalPageResponseDto.of(page);
    }
}
