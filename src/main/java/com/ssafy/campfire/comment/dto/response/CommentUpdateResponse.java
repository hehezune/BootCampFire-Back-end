package com.ssafy.campfire.comment.dto.response;

public record CommentUpdateResponse(

        /**
         * id : 댓글 pk
         * boardId : 글 pk
         * user : 댓글 작성자
         * content : 댓글 내용
         * anonymous : 익명여부
         * ref : 댓글 number
         * refOrder : 댓글 순서
         */

        Long id,
        Long boardId,
        String user,
        String content,
        Boolean anonymous,
        int ref,
        int refOrder
) {
}
