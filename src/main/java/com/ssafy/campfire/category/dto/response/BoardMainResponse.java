package com.ssafy.campfire.category.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record BoardMainResponse(

        /**
         * id : 게시글 pk
         * title : 제목
         */

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
