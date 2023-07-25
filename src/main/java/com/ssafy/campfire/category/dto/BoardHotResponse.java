package com.ssafy.campfire.category.dto;

import com.ssafy.campfire.board.domain.Board;

public record BoardHotResponse(
        Long id,
        String category,
        String title,
        Integer likeCnt,
        Integer commentCnt
) {
    public static BoardHotResponse of(Board board) {
        return new BoardHotResponse(
                board.getId(),
                board.getCategory().getName().getMessage(),
                board.getTitle(),
                board.getLikeCnt(),
                board.getCommentCnt()
        );
    }
}
