package com.ssafy.campfire.category.repository;

import com.ssafy.campfire.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomCategoryRepository {

    // main 화면
    List<Board> getHotFiveBoard();
    List<Board> getLatestFiveBoard(Long categoryId);

    // 정렬용
    Page<Board> getBoardByNewest(Long categoryId, Pageable pageable);
    Page<Board> getBootBoardByNewest(Long categoryId,Long bootcampId, Pageable pageable);
    Page<Board> getBoardByLike(Long categoryId, Pageable pageable);
    Page<Board> getBootBoardByLike(Long categoryId, Long bootcampId, Pageable pageable);
    Page<Board> getBoardByView(Long categoryId, Pageable pageable);
    Page<Board> getBootBoardByView(Long categoryId, Long bootcampId, Pageable pageable);

    // 검색용
    Page<Board> getMainSearchByTitleContent(String keyword, Pageable pageable);
    Page<Board> getMainSearchByNickname(String nickname, Pageable pageable);
    Page<Board> getSearchByTitleContent(Long categoryId, String keyword, Pageable pageable);
    Page<Board> getSearchByNickname(Long categoryId, String nickname, Pageable pageable);
    Page<Board> getBootSearchByTitleContent(Long categoryId, Long bootcampId, String keyword, Pageable pageable);
    Page<Board> getBootSearchByNickname(Long categoryId, Long bootcampId, String nickname, Pageable pageable);
}
