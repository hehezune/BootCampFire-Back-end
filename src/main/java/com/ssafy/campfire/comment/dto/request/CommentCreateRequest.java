package com.ssafy.campfire.comment.dto.request;

import com.ssafy.campfire.comment.domain.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CommentCreateRequest(

        /**
         * boardId : 글 pk
         * userId : 댓글 작성자 pk
         * content : 댓글 내용
         * anonymous : 익명여부
         * preCommentId : 대댓일때 부모 ID
         */

        @NotNull(message = "게시글  필수입니다.")
        Long boardId,

        @NotNull(message = "글쓴이는 필수입니다.")
        Long userId,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotNull(message = "익명여부는 필수입니다.")
        Boolean anonymous,

        //대댓이면 부모댓글 ID보냄
        Long preCommentId

) {
    public Comment toEntity(){
        return new Comment(
                this.content,
                this.anonymous
        );
    }
}
