package com.ssafy.campfire.bootcamp.domain;

import com.ssafy.campfire.utils.domain.BaseEntity;
import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity
@Table(name = "bootcamp") // DB 이름과 동일하게
public class Bootcamp extends BaseEntity {
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

    private String description;

    private Double cost;

    private Boolean card;

    private Boolean support;

    @Column(name = "has_codingtest")
    private Boolean hasCodingtest;

    private String on_off;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name ="review_cnt", nullable = false)
    private Integer reviewCnt;

    @Column(name = "total_score")
    private Double totalScore;

    @Column(name ="algo_cnt",nullable = false)
    private Integer algoCnt;


    public void updateTotalScore(double score){
        this.totalScore += score;
        this.reviewCnt++;
    }

    @Builder
    public Bootcamp(String name){
        this.name = name;
        this.algoCnt = 0;
        this.reviewCnt = 0;
    }

    @Builder
    public Bootcamp(String name, String siteUrl, String process,
                    String schedule, String description, Double cost, Boolean card, Boolean support,
                    Boolean hasCodingtest, String on_off, LocalDateTime startDate, LocalDateTime endDate){

        this.name = name;
        this.siteUrl = siteUrl;
        this.process = process;
        this.schedule = schedule;
        this.description = description;
        this.cost = cost;
        this.card = card;
        this.support = support;
        this.hasCodingtest = hasCodingtest;
        this.on_off = on_off;
        this.startDate = startDate;
        this.endDate = endDate;
        this.algoCnt = 0;
        this.reviewCnt = 5;
        this.totalScore = 5.0;
//        this.createdDate = LocalDateTime.now();
    }

}
