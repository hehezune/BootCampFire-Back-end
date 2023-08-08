package com.ssafy.campfire.likes.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record LikesResponse(

        /**
         * likes : 좋아요수
         */

        int likes
) {
    public static LikesResponse from(Board board) {
        return new LikesResponse(
                board.getLikeCnt()
        );
    }
}