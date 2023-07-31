package com.ssafy.campfire.comment.dto.request;

import com.ssafy.campfire.comment.domain.Comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CommentCreateRequest(
        @NotNull(message = "게시글  필수입니다.")
        Long boardId,

        @NotNull(message = "글쓴이는 필수입니다.")
        Long userId,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotNull(message = "익명여부는 필수입니다.")
        Boolean anonymous,

        //대댓이면 부모 ref 보냄
        Integer ref,

        //대댓이면 부모 ref의 maxOrder 보냄
        Integer refOrder
) {
    public Comment toEntity(){
        return new Comment(
                this.content,
                this.anonymous
        );
    }
}
