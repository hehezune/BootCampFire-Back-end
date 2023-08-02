package com.ssafy.campfire.comment.dto.response;

import com.ssafy.campfire.comment.domain.Comment;

import java.time.LocalDateTime;

public record CommentReadResponse(
        Long id,
        String user,
        String bootcamp,
        String content,
        Integer ref,
        Integer refOrder,
        Boolean isWriter,
        LocalDateTime createdDate
) {
    public static CommentReadResponse of(Comment comment){

        Boolean isWriter = false;
        if(comment.getBoard().getUser().getId() == comment.getUser().getId())
            isWriter = true;

        if(comment.getAnonymous()) {
            return new CommentReadResponse(
                    comment.getId(),
                    "익명",
                    "익명의 캠프",
                    comment.getContent(),
                    comment.getRef(),
                    comment.getRefOrder(),
                    isWriter,
                    comment.getCreatedDate()
            );
        }
        return new CommentReadResponse(
                comment.getId(),
                comment.getUser().getNickname(),
                comment.getUser().getBootcamp().getName(),
                comment.getContent(),
                comment.getRef(),
                comment.getRefOrder(),
                isWriter,
                comment.getCreatedDate()
        );
    }
}
