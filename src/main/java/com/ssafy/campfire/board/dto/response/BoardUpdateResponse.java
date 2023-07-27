package com.ssafy.campfire.board.dto.response;

public record BoardUpdateResponse(
        Long id,
        String category,
        String user,
        String title,
        String content,
        Boolean anonymous
) {
}
