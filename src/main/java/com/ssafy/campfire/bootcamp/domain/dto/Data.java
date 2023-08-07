package com.ssafy.campfire.bootcamp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    /*
        - name : 부트캠프 이름
        siteUrl : 사이트 주소
        process : 모집 절차
        - schedule : 교육기간
        description : 설명
        - cost : 수강료
        - card : 내일 배움카드 여부
        support : 지원금 여부
        - hasCodingtest : 코딩테스트 여부
        - onOff : 온오프라인
        - startDate : 모집 시작일
        - endDate : 모집 마감일
        imgUrl : 이미지 주소
        - tracks : 트랙 리스트
        - languages : 언어 리스트
        - regions : 지역 리스트
    */


    private String brandName; //부트캠프 이름
    private String campId; //(campId)_(batchId)로 상세 url 알수 있음
    private String batchId;
    private String startDate;//개강일
    private String endDate;//종강일
    private Long tuition; // 수강료
    private Boolean nbcardRequired; //내일배움카드
    private String passRequiredOption;
    private String onoff;
    private String regDate; //모집 시작일
    private String regEndDate; // 모집 마감일
    private List<String> categories;
    private List<String> city;
    private List<String> keywords;

    private String campUrl;
    private List<String> process;
    private String description;
    private Boolean support;
    private String imgUrl;



    public String detailUrl(){
        return "/"+campId+"_"+batchId;
    }


}
