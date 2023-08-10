package com.ssafy.campfire.utils.crawling.enums;

public enum ApplicationProcessType {
    requestCounsel("상담신청"),
    offlineCounsel("개별상담(대면)"),
    onlineCounsel("개별상담(비대면)"),

    textCounsel("개별상담(채팅)"),
    preApply("사전신청"),
    formApply("신청서(서류)작성"),
    homepageSubmit("홈페이지에서 수강신청"),
    hrd("HRD-Net 수강신청"),
    selfIntroduction("자기소개서"),
    onlineInterview("인터뷰(비대면)"),
    offlineInterview("인터뷰(대면)"),
    AIInterview("인터뷰"),
    GroupInterview("그룹면접"),
    levelTest("레벨(역량)테스트"),
    preCourse("사전교육(프리코스)"),
    codingTest("코딩테스트"),
    aptitudeTest("인적성검사"),
    taskWithCoding("과제(코딩포함)"),
    taskWithoutCoding("과제(코딩미포함)"),
    jobTest("직업검사"),
    physicalExamination("신체검사");
    private final String message;

    ApplicationProcessType(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
