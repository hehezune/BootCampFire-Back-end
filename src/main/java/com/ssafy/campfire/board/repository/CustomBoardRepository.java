package com.ssafy.campfire.board.repository;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.dto.response.UserBoardListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface CustomBoardRepository {

    /**
     * slice
     */
    Slice<UserBoardListResponse> getUserBoard(Long userId, Pageable pageable);

    /**
     * page
     */
//    Page<Board> getUserBoard(Long userId, Pageable pageable);
    Optional<Board> getByIdFetchJoin(Long boardId);
}
