package com.ssafy.campfire.utils.crawling.enums;

public enum CategoryType {
    infra("인프라"),
    data("데이터"),
    web("웹"),
    game("게임"),
    embeded("임베디드"),
    mobile("모바일"),
    dt("마케팅"),
    design("디자인"),
    startup("창업"),
    cs("CS 또는 Algorithm");
    private final String message;

    CategoryType(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
