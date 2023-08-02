package com.ssafy.campfire.comment.dto.request;

import com.ssafy.campfire.comment.domain.dto.CommentUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record CommentUpdateRequest(
        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotNull(message = "익명여부는 필수입니다.")
        Boolean anonymous
) {
    public CommentUpdate toDto() {
        return new CommentUpdate(this.content, this.anonymous);
    }
}
