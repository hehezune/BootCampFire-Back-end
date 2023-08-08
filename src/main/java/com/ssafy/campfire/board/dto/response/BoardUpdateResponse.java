package com.ssafy.campfire.board.dto.response;

public record BoardUpdateResponse(

        /**
         * id : 게시글 pk
         * category : 글에 해당하는 카테고리
         * user : 글쓴이(로그인한 유저)
         * title : 제목
         * content : 내용
         * anonymous : 익명여부
         */

        Long id,
        String category,
        String user,
        String title,
        String content,
        Boolean anonymous
) {
}
