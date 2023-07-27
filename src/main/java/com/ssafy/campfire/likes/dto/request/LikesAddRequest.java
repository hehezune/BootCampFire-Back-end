package com.ssafy.campfire.likes.dto.request;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.likes.domain.Likes;
import com.ssafy.campfire.user.domain.User;

public record LikesAddRequest(
        User user,
        Board board
) {
    public Likes toEntity() {
        return Likes.builder()
                .user(this.user)
                .board(this.board)
                .build();
    }
}
