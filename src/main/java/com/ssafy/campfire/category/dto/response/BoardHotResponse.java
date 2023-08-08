package com.ssafy.campfire.category.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record BoardHotResponse(

        /**
         * id : 게시글 pk
         * category : 카테고리
         * title : 제목
         * likeCnt : 좋아요 수
         * commentCnt : 댓글 수
         */

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
