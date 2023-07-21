package com.ssafy.campfire.category.repository;

import com.ssafy.campfire.board.domain.Board;

import java.util.List;

public interface CustomCategoryRepository {
    List<Board> getLatestFiveBoard(Long categoryId);
}
