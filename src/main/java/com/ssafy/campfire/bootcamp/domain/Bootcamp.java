package com.ssafy.campfire.bootcamp.domain;

import com.ssafy.campfire.utils.domain.BaseEntity;
import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor //기본 생성자 생성
@Entity
@ToString
@Table(name = "bootcamp") // DB 이름과 동일하게
public class Bootcamp extends BaseEntity {

     /*
        id : pk
        name : 부트캠프 이름
        siteUrl : 사이트 주소
        process : 모집 절차
        schedule : 교육기간
        description : 설명
        cost : 수강료
        card : 내일 배움카드 여부
        support : 지원금 여부
        hasCodingtest : 코딩테스트 여부
        onOff : 온오프라인
        startDate : 모집 시작일
        endDate : 모집 마감일
        imgUrl : 이미지 주소
        reviewCnt : 리뷰 갯수
        totalScore : 리뷰 총점
        algoCnt : 알고리즘 푼 인원
    */


    //field
    @Id //부트캠프 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동생성 - identity는 기본키 자동생성 방법을 데이터베이스에 위임함.
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "site_url")
    private String siteUrl;

    private String process;

    private String schedule;

    @Column(length = 5000)
    private String description;

    private Double cost;

    private Boolean card;

    private Boolean support;

    @Column(name = "has_codingtest")
    private Boolean hasCodingtest;

    @Column(name ="on_off")
    private String onOff;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "img_url", length = 2000)
    private String imgUrl;

    @Column(name ="review_cnt", nullable = false)
    private Integer reviewCnt;

    @Column(name = "total_score")
    private Double totalScore;

    @Column(name ="algo_cnt",nullable = false)
    private Integer algoCnt;


    public void addTotalScore(double score){
        this.totalScore += score;
        this.reviewCnt++;
        updatedDate = LocalDateTime.now();
    }

    public void subTotalScore(double score){
        this.totalScore -= score;
        this.reviewCnt--;
        updatedDate = LocalDateTime.now();
    }
    @Builder
    public Bootcamp(String name){
        this.name = name;
        this.algoCnt = 0;
        this.reviewCnt = 0;
    }

    public void addAlgoCnt(){
        algoCnt++;
        updatedDate = LocalDateTime.now();
    }

    @Builder
    public Bootcamp(String name, String siteUrl, String process,
                    String schedule, String description, Double cost, Boolean card, Boolean support,
                    Boolean hasCodingtest, String onOff, LocalDateTime startDate, LocalDateTime endDate, String imgUrl){

        this.name = name;
        this.siteUrl = siteUrl;
        this.process = process;
        this.schedule = schedule;
        this.description = description;
        this.cost = cost;
        this.card = card;
        this.support = support;
        this.hasCodingtest = hasCodingtest;
        this.onOff = onOff;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imgUrl = imgUrl;
        this.algoCnt = 0;
        this.reviewCnt = 0;
        this.totalScore = 0.0;
        this.createdDate = LocalDateTime.now();
    }


    public void update(Bootcamp bootcamp) {
        this.name = bootcamp.getName();
        this.siteUrl = bootcamp.getSiteUrl();
        this.process = bootcamp.getProcess();
        this.schedule = bootcamp.getSchedule();
        this.description = bootcamp.getDescription();
        this.cost = bootcamp.getCost();
        this.card = bootcamp.getCard();
        this.support = bootcamp.getSupport();
        this.hasCodingtest = bootcamp.getHasCodingtest();
        this.onOff = bootcamp.getOnOff();
        this.startDate = bootcamp.getStartDate();
        this.endDate = bootcamp.getEndDate();
        this.imgUrl = bootcamp.getImgUrl();
        this.updatedDate = LocalDateTime.now();
    }

    public void updateDescription(String description) {
        this.description += "\n" + description;
        this.updatedDate = LocalDateTime.now();
    }
}
