package com.ssafy.campfire.comment.dto.response;

import com.ssafy.campfire.comment.domain.Comment;

public record CommentCreateResponse(
        Long id,
        Long boardId,
        String user,
        String content,
        Boolean anonymous
) {
    public static CommentCreateResponse from(Comment comment){
        return new CommentCreateResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getNickname(),
                comment.getContent(),
                comment.getAnonymous()
        );
    }
}
