package com.ssafy.campfire.comment.domain.dto;

public record CommentUpdate(

        /**
         * content : 댓글 내용
         * anonymous : 익명여부
         */

        String content,
        boolean anonymous
) {
}
