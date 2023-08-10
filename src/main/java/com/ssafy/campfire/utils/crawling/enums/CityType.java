package com.ssafy.campfire.utils.crawling.enums;

public enum CityType {
    cn("충남"),
    busan("부산"),
    sejong("세종"),
    gg("경기"),
    seoul("서울"),
    gn("경남"),
    gwangju("광주"),
    incheon("인천"),
    gb("경북"),
    jb("전북"),
    daejun("대전"),
    gw("강원"),
    jn("전남"),
    cb("충북"),
    daegu("대구"),
    ulsan("울산"),
    abroad("해외");
    private final String message;

    CityType(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
