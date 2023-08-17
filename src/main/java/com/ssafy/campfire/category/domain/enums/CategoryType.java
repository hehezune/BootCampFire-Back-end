package com.ssafy.campfire.category.domain.enums;

public enum CategoryType {
    FREE("자유"),
    LOVE("썸/연애"),
    HEALTH("헬스/스포츠"),
    STUDY("스터디"),
    PROJECT("프로젝트"),
    IT("IT"),
    WORRY("고민"),
    QUESTION("질문"),
    BOOTCAMP("부트캠프");

    private final String message;

    CategoryType(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
