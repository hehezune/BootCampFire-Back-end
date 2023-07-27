package com.ssafy.campfire.board.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record BoardCreateResponse(
    Long id,
    String category,
    String user,
    String title,
    String content,
    Boolean anonymous
) {
    public static BoardCreateResponse from(Board board){
        return new BoardCreateResponse(
                board.getId(),
                board.getCategory().getName().getMessage(),
                board.getUser().getNickname(),
                board.getTitle(),
                board.getContent(),
                board.getAnonymous()
        );
    }
}
