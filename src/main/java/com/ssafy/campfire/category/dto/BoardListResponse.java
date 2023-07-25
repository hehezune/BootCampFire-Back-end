package com.ssafy.campfire.category.dto;

import com.ssafy.campfire.board.domain.Board;

public record BoardListResponse(
        Long id,
        String title,
        String content,
        String bootcamp,
        String writer,
        String category,
        Integer commentCnt,
        Integer likeCnt,
        Integer view
) {
    public static BoardListResponse of(Board board) {

        if (board.getAnonymous())
            return new BoardListResponse(
                    board.getId(),
                    board.getTitle(),
                    board.getContent(),
                    "익명의 캠프",
                    "익명",
                    board.getCategory().getName().getMessage(),
                    board.getCommentCnt(),
                    board.getLikeCnt(),
                    board.getView()
            );

        return new BoardListResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUser().getBootcamp().getName(),
                board.getUser().getNickname(),
                board.getCategory().getName().getMessage(),
                board.getCommentCnt(),
                board.getLikeCnt(),
                board.getView()
        );
    }
}
