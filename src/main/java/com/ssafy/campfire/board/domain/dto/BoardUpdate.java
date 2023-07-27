package com.ssafy.campfire.board.domain.dto;

public record BoardUpdate(
        String title,
        String content,
        Boolean anonymous
) {
}
