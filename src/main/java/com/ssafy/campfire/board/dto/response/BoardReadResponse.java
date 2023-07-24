package com.ssafy.campfire.board.dto.response;

import com.ssafy.campfire.bootcamp.domain.QBootcamp;

public record BoardReadResponse(
        Long id,
        String title,
        String content,
        String bootcamp,
        String writer,
        Boolean isWriter,
        Integer commentCnt,
        Integer likeCnt,
        Integer view,
        Boolean isLike
) {
    public static BoardReadResponse from(
            Long id,
            String title,
            String content,
            String bootcamp,
            String writer,
            Boolean isWriter,
            Integer commentCnt,
            Integer likeCnt,
            Integer view,
            Boolean isLike
    ){
        return new BoardReadResponse(
                id,
                title,
                content,
                bootcamp,
                writer,
                isWriter,
                commentCnt,
                likeCnt,
                view,
                isLike
        );
    }
}
