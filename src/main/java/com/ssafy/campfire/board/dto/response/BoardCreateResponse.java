package com.ssafy.campfire.board.dto.response;

import com.ssafy.campfire.board.domain.Board;

public record BoardCreateResponse(

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
    public static BoardCreateResponse from(Board board){
        return new BoardCreateResponse(
                board.getId(),
                board.getCategory().getName().getMessage(),
                board.getUser().getNickname(),
                board.getTitle(),
                board.getContent(),
                board.getAnonymous()
        );
    }
}
