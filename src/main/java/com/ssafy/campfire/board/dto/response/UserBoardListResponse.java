package com.ssafy.campfire.board.dto.response;

import com.ssafy.campfire.board.domain.Board;

import java.time.LocalDateTime;

public record UserBoardListResponse(
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
