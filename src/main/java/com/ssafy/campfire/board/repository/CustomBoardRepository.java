package com.ssafy.campfire.board.repository;

import com.ssafy.campfire.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomBoardRepository {
    Page<Board> getUserBoard(Long userId, Pageable pageable);
    Optional<Board> getByIdFetchJoin(Long boardId);
}
