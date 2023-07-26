package com.ssafy.campfire.board.dto.request;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.user.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record BoardCreateRequest(

        @NotNull(message = "카테고리는 필수입니다.")
        Category category,

        @NotNull(message = "글쓴이는 필수입니다.")
        User user,

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotBlank(message = "내용은 필수입니다.")
        String content,

        @NotNull(message = "익명여부는 필수입니다.")
        Boolean anonymous
) {
        public Board toEntity(){
                return new Board(
                        this.user,
                        this.title,
                        this.content,
                        this.anonymous
                );
        }
}
