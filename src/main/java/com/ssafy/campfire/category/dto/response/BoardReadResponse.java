package com.ssafy.campfire.category.dto.response;

public record BoardReadResponse(
        Long id,
        Long userId,
        Long categoryId,
        String title,
        String content,
        Boolean anonymous,
        Integer commentCnt,
        Integer likeCnt,
        Integer view
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
            Integer view
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
                view
        );
    }
}
