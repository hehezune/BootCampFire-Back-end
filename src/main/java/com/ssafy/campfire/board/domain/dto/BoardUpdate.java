package com.ssafy.campfire.board.domain.dto;


public record BoardUpdate(

        /**
         * title : 제목
         * content : 내용
         * anonymous : 익명여부
         */

        String title,
        String content,
        boolean anonymous
) {
}
