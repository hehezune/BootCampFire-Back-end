package com.ssafy.campfire.utils.crawling.dto;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Language;
import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.domain.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private String onoff;
    private String regDate; //모집 시작일
    private String regEndDate; // 모집 마감일
    private List<String> categories;
    private List<String> city;
    private List<String> keywords;
    private String campUrl;
    private Object applicationProcess;
    private Explanation explanations;
    private String title;


    private String etcSubsidyText;

    private String imgUrl;


    public String detailUrl(){
        return "/"+campId+"_"+batchId;
    }
    public void setPassRequiredOption(String passRequiredOption){
        this.passRequiredOption = passRequiredOption;
    }

    public Bootcamp toBootcamp() throws ParseException {
        String name = brandName;
        String siteUrl = campUrl;
        String process = applicationProcess.toString(); //데이터 처리 필요
        String schedule = startDate+" ~ "+endDate;
        String description = title + ((explanations != null && explanations.summary !=null ) ? ("\n"+explanations.summary) : "");
        Double cost = tuition.doubleValue();
        Boolean card = nbcardRequired;
        Boolean support = etcSubsidyText != null && etcSubsidyText.contains("만원"); //"만원"이라는 글자가 있는지 확인
        Boolean hasCodingtest = passRequiredOption.equals("passRequiredWithCoding");
        String onOff = onoff.equals("mix") ? "온오프라인" : onoff.equals("online") ? "온라인" : "오프라인";
        LocalDateTime startDate = LocalDateTime.parse(regDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDate = LocalDateTime.parse(regEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String imgUrl = null;
        List<Track> tracks = null;
        List<Language> languages = null;
        List<Region> regions = null;
        return new Bootcamp( name, siteUrl,  process, schedule, description,  cost,card,support,hasCodingtest,onOff,startDate,endDate, imgUrl);
    }
}
