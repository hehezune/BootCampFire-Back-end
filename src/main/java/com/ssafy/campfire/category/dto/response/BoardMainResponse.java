package com.ssafy.campfire.category.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record BoardMainResponse(
        Long id,
        String title
) {
    public static BoardMainResponse of(Board board) {
        return new BoardMainResponse(
                board.getId(),
                board.getTitle()
        );
    }
}
