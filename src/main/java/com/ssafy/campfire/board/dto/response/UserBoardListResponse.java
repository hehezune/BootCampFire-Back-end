package com.ssafy.campfire.board.dto.response;

import com.ssafy.campfire.board.domain.Board;

import java.time.LocalDateTime;

public record UserBoardListResponse(

        /**
         * id : 게시글 pk
         * title : 제목
         * content : 내용
         * bootcamp : 글쓴이의 부캠
         * writer : 글쓴이
         * category : 글에 해당하는 카테고리
         * commentCnt : 댓글 수
         * likeCnt : 좋아요 수
         * view : 조회수
         * createDate : 작성일자
         */

        Long id,
        String title,
        String content,
        String bootcamp,
        String writer,
        String category,
        Integer commentCnt,
        Integer likeCnt,
        Integer view,
        LocalDateTime createdDate
) {
    public static UserBoardListResponse of(Board board) {
        return new UserBoardListResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getUser().getBootcamp().getName(),
                board.getUser().getNickname(),
                board.getCategory().getName().getMessage(),
                board.getCommentCnt(),
                board.getLikeCnt(),
                board.getView(),
                board.getCreatedDate()
        );
    }
}
