package com.ssafy.campfire.comment.dto.response;

import com.ssafy.campfire.comment.domain.Comment;

import java.time.LocalDateTime;

public record CommentCreateResponse(

        /**
         * id : 댓글 pk
         * boardId : 글 pk
         * user : 댓글 작성자
         * content : 댓글 내용
         * anonymous : 익명여부
         * createdDate : 작성일
         * ref : 댓글 number
         * refOrder : 댓글 순서
         */

        Long id,
        Long boardId,
        String user,
        String content,
        Boolean anonymous,
        LocalDateTime createdDate,
        int ref,
        int refOrder
) {
    public static CommentCreateResponse from(Comment comment){
        return new CommentCreateResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getNickname(),
                comment.getContent(),
                comment.getAnonymous(),
                comment.getCreatedDate(),
                comment.getRef(),
                comment.getRefOrder()
        );
    }
}
