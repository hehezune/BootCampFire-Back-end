package com.ssafy.campfire.board.dto.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record BoardReadResponse(

        /**
         * id : 게시글 pk
         * title : 제목
         * content : 내용
         * bootcamp : 글쓴이의 부캠
         * writer : 글쓴이
         * isWriter : 로그인 한 유저가 글쓴이인가?
         * commentCnt : 댓글 수
         * likeCnt : 좋아요 수
         * view : 조회수
         * isLike : 로그인한 유저가 좋아요 눌렀는지 여부
         * createDate : 작성일자
         */

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
        String createdDate
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
                createdDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
