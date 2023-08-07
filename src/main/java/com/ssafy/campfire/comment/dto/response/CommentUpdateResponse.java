package com.ssafy.campfire.comment.dto.response;

public record CommentUpdateResponse(
        Long id,
        Long boardId,
        String user,
        String content,
        Boolean anonymous,
        int ref,
        int refOrder
) {
}
