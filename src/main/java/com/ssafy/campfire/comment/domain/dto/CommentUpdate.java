package com.ssafy.campfire.comment.domain.dto;

public record CommentUpdate(
        String content,
        boolean anonymous
) {
}
