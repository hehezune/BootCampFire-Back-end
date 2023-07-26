package com.ssafy.campfire.board.dto.response;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.user.domain.User;

public record BoardCreateResponse(
    Long id,
    Category category,
    User user,
    String title,
    String content,
    Boolean anonymous
) {
    public static BoardCreateResponse from(Board board){
        return new BoardCreateResponse(
                board.getId(),
                board.getCategory(),
                board.getUser(),
                board.getTitle(),
                board.getContent(),
                board.getAnonymous()
        );
    }
}
