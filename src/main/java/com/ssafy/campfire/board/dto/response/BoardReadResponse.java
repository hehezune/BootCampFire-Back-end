package com.ssafy.campfire.board.dto.response;

public record BoardReadResponse(
        Long id,
        Long userId,
        Long categoryId,
        String title,
        String content,
        Boolean anonymous,
        Integer commentCnt,
        Integer likeCnt,
        Integer view,
        Boolean isLike
) {
    public static BoardReadResponse from(
            Long id,
            Long userId,
            Long categoryId,
            String title,
            String content,
            Boolean anonymous,
            Integer commentCnt,
            Integer likeCnt,
            Integer view,
            Boolean isLike
    ){
        return new BoardReadResponse(
                id,
                userId,
                categoryId,
                title,
                content,
                anonymous,
                commentCnt,
                likeCnt,
                view,
                isLike
        );
    }
}
