package com.ssafy.campfire.comment.dto.response;

import com.ssafy.campfire.comment.domain.Comment;

import java.time.LocalDateTime;

public record CommentReadResponse(
        Long id,
        String user,
        String bootcamp,
        String content,
        LocalDateTime createdDate
) {
    public static CommentReadResponse of(Comment comment){
        return new CommentReadResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getBootcamp().getName(),
                comment.getContent(),
                comment.getCreatedDate()
        );
    }
}
