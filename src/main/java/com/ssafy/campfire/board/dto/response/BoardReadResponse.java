package com.ssafy.campfire.board.dto.response;

import java.time.LocalDateTime;

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
        Boolean isLike,
        LocalDateTime createdDate
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
            Boolean isLike,
            LocalDateTime createdDate
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
                isLike,
                createdDate
        );
    }
}
