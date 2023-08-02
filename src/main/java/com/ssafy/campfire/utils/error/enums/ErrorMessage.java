package com.ssafy.campfire.utils.error.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    INTERVAL_SERVER_ERROR(INTERNAL_SERVER_ERROR, "요청을 처리하는 과정에서 서버가 예상하지 못한 오류가 발생하였습니다."),
    USER_NOT_FOUND(NOT_FOUND, "해당 회원을 찾을 수 없습니다."),
    CATEGORY_NOT_FOUND(NOT_FOUND, "해당 카테고리를 찾을 수 없습니다."),
    BOARD_NOT_FOUND(NOT_FOUND, "해당 게시글 찾을 수 없습니다."),
    BOOTCAMP_NOT_FOUND(NOT_FOUND, "해당 부트캠프를 찾을 수 없습니다."),
    REGION_NOT_FOUND(NOT_FOUND, "해당 지역을 찾을 수 없습니다."),
    REVIEW_NOT_FOUND(NOT_FOUND, "해당 리뷰 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "해당 댓글 찾을 수 없습니다."),
    LIKE_NOT_FOUND(NOT_FOUND, "해당 좋아요를 찾을 수 없습니다."),
    INVALID_BOARD_REQUEST(BAD_REQUEST, "해당 게시물 요청이 적절하지 않습니다."),
    INVALID_CATEGORY_REQUEST(BAD_REQUEST, "해당 카테고리 요청이 적절하지 않습니다."),
    INVALID_BOOTCAMP_REQUEST(BAD_REQUEST, "해당 부트캠프 요청이 적절하지 않습니다."),
    INVALID_REVIEW_REQUEST(BAD_REQUEST, "해당 리뷰 요청이 적절하지 않습니다."),
    INVALID_COMMENT_REQUEST(BAD_REQUEST, "해당 댓글 요청이 적절하지 않습니다."),
    DUPLICATE_ALGORITHM_REQUEST(CONFLICT, "해당 날짜에 이미 알고리즘이 존재합니다."),
    INVALID_FILE_UPLOAD(BAD_REQUEST, "유효하지 않은 파일 업로드입니다.");

    private final int code;
    private final String phrase;

    ErrorMessage(HttpStatus code, String phrase) {
        this.code = code.value();
        this.phrase = phrase;
    }
}
