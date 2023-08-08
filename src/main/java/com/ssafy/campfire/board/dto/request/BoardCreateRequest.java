package com.ssafy.campfire.board.dto.request;

import com.ssafy.campfire.board.domain.Board;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record BoardCreateRequest(

        /**
         * categoryId : 글에 해당하는 카테고리
         * userId : 글쓴이(로그인한 유저)
         * title : 제목
         * content : 내용
         * anonymous : 익명여부
         */

        @NotNull(message = "카테고리는 필수입니다.")
        Long categoryId,

        @NotNull(message = "글쓴이는 필수입니다.")
        Long userId,

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotNull(message = "익명여부는 필수입니다.")
        Boolean anonymous
) {
        public Board toEntity(){
                return new Board(
                        this.title,
                        this.content,
                        this.anonymous
                );
        }
}
