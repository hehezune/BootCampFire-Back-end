package com.ssafy.campfire.category.repository;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.category.dto.response.BoardListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CustomCategoryRepository {

    // main 화면
    List<Board> getHotFiveBoard();
    List<Board> getLatestFiveBoard(Long categoryId);

    /**
     * Slice
     */
    // 정렬용
    Slice<BoardListResponse> getBoardByNewest(Long categoryId, Pageable pageable);
    Slice<BoardListResponse> getBootBoardByNewest(Long categoryId,Long bootcampId, Pageable pageable);
    Slice<BoardListResponse> getBoardByLike(Long categoryId, Pageable pageable);
    Slice<BoardListResponse> getBootBoardByLike(Long categoryId, Long bootcampId, Pageable pageable);
    Slice<BoardListResponse> getBoardByView(Long categoryId, Pageable pageable);
    Slice<BoardListResponse> getBootBoardByView(Long categoryId, Long bootcampId, Pageable pageable);

    // 검색용
    Slice<BoardListResponse> getMainSearchByTitleContent(String keyword, Pageable pageable);
    Slice<BoardListResponse> getMainSearchByNickname(String nickname, Pageable pageable);
    Slice<BoardListResponse> getSearchByTitleContent(Long categoryId, String keyword, Pageable pageable);
    Slice<BoardListResponse> getSearchByNickname(Long categoryId, String nickname, Pageable pageable);
    Slice<BoardListResponse> getBootSearchByTitleContent(Long categoryId, Long bootcampId, String keyword, Pageable pageable);
    Slice<BoardListResponse> getBootSearchByNickname(Long categoryId, Long bootcampId, String nickname, Pageable pageable);

    /**
     * page
     */
    // 정렬용
//    Page<Board> getBoardByNewest(Long categoryId, Pageable pageable);
//    Page<Board> getBootBoardByNewest(Long categoryId,Long bootcampId, Pageable pageable);
//    Page<Board> getBoardByLike(Long categoryId, Pageable pageable);
//    Page<Board> getBootBoardByLike(Long categoryId, Long bootcampId, Pageable pageable);
//    Page<Board> getBoardByView(Long categoryId, Pageable pageable);
//    Page<Board> getBootBoardByView(Long categoryId, Long bootcampId, Pageable pageable);
//
//    // 검색용
//    Page<Board> getMainSearchByTitleContent(String keyword, Pageable pageable);
//    Page<Board> getMainSearchByNickname(String nickname, Pageable pageable);
//    Page<Board> getSearchByTitleContent(Long categoryId, String keyword, Pageable pageable);
//    Page<Board> getSearchByNickname(Long categoryId, String nickname, Pageable pageable);
//    Page<Board> getBootSearchByTitleContent(Long categoryId, Long bootcampId, String keyword, Pageable pageable);
//    Page<Board> getBootSearchByNickname(Long categoryId, Long bootcampId, String nickname, Pageable pageable);
}
