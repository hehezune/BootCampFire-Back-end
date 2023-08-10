package com.ssafy.campfire.utils.crawling.dto;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.utils.crawling.enums.ApplicationProcessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.w3c.dom.html.HTMLTableElement;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
    private String passRequiredOption; // 코딩 유뮤
    private String onoff; //온오프라인 유뮤
    private String regDate; //모집 시작일
    private String regEndDate; // 모집 마감일
    private List<String> categories;
    private List<String> city;
    private List<String> keywords;
    private String campUrl; //부트캠프 홈페이지 url
    private ArrayList<ArrayList> applicationProcess; //지원 절차


    private String title; //부트캠프 제목
    private String etcSubsidyText; // 지원내용
    private String imgUrl;


//    public String detailUrl(){
//        return "/"+campId+"_"+batchId;
//    }
//    public void setCampUrl(String campUrl) {
//        this.campUrl = campUrl;
//    }

    public Bootcamp toBootcamp() throws ParseException {
        String name = brandName;
//        String siteUrl = campUrl;
        String siteUrl = "https://boottent.sayun.studio/camps/"+campId+"_"+batchId;
        String process = applicationProcessToString(applicationProcess);
        String schedule = getSchedule(startDate, endDate);
        String description = title;
        Double cost = tuition.doubleValue();
        Boolean card = nbcardRequired;
        Boolean support  = etcSubsidyText != null && etcSubsidyText.contains("원"); //"원"이라는 글자가 있는지 확인
        Boolean hasCodingtest = passRequiredOption.equals("passRequiredWithCoding");
        String onOff = onoff.equals("mix") ? "온오프라인" : onoff.equals("online") ? "온라인" : "오프라인";
        LocalDateTime startDate = regDateToLocalDateTime(regDate);
        LocalDateTime endDate = regEndDateToLocalDateTime(regEndDate);
        return new Bootcamp( name, siteUrl,  process, schedule, description,  cost,card,support,hasCodingtest,onOff,startDate,endDate, imgUrl);
    }

    private LocalDateTime regEndDateToLocalDateTime(String regEndDate) {
        if(Integer.parseInt(regEndDate.substring(8,10)) > 31){
            System.out.println("date = " + regEndDate);
            regEndDate = regEndDate.substring(0, 7) +("-30 23:59");
            System.out.println("date = " + regEndDate);
        }
        return LocalDateTime.parse(regEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }

    private LocalDateTime regDateToLocalDateTime(String regDate) {
        if(regDate.length() == 13){ //2023-07 00:00
            regDate =  regDate.substring(0, 7) + "-01 "+regDate.substring(8);
        }
        if(Integer.parseInt(regDate.substring(8,10)) > 31){
            return LocalDateTime.now();
        }
        return LocalDateTime.parse(regDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    }
    private String getSchedule(String startDate, String endDate) {
        if(Integer.parseInt(startDate.substring(8,10)) > 31){
            startDate =  startDate.substring(0, 7) +("월 중");
        }

        if(Integer.parseInt(endDate.substring(8,10)) > 31){
            endDate = endDate.substring(0, 7) +("월 중");
        }

        return startDate +" ~ "+ endDate;
    }



    private String applicationProcessToString(ArrayList<ArrayList> applicationProcess) {
        if(applicationProcess == null) return "";
        StringBuilder sb = new StringBuilder();

        for ( ArrayList list: applicationProcess) {
            if(list == null) continue;
            for (Object obj : list) {
                sb.append(ApplicationProcessType.valueOf(obj.toString()).getMessage()).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
            sb.append(" → ");
        }
        sb.delete(sb.length()-3, sb.length());
        return  sb.toString();
    }


}
