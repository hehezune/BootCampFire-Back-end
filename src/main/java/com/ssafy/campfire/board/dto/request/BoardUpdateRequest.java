package com.ssafy.campfire.board.dto.request;

import com.ssafy.campfire.board.domain.dto.BoardUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record BoardUpdateRequest(

        /**
         * title : 제목
         * content : 내용
         * anonymous : 익명여부
         */

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotNull(message = "익명여부는 필수입니다.")
        Boolean anonymous
) {
    public BoardUpdate toDto() {
        return new BoardUpdate(this.title, this.content, this.anonymous);
    }
}
