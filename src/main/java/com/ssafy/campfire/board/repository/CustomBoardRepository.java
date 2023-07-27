package com.ssafy.campfire.board.repository;

import com.ssafy.campfire.board.domain.Board;

import java.util.Optional;

public interface CustomBoardRepository {
    Optional<Board> getByIdFetchJoin(Long boardId);
}
