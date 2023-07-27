package com.ssafy.campfire.likes.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record LikesResponse(
        int likes
) {
    public static LikesResponse from(Board board) {
        return new LikesResponse(
                board.getLikeCnt()
        );
    }
}