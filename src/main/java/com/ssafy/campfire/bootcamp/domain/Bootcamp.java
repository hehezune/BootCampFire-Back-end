package com.ssafy.campfire.bootcamp.domain;

import com.ssafy.campfire.utils.domain.BaseEntity;
import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


    public void updateReviewCnt(){
        this.reviewCnt++;

    }
    public void updateTotalScore(double score){
        this.totalScore += score;
    }

    @Builder
    public Bootcamp(String name){
        this.name = name;
        this.algoCnt = 0;
        this.reviewCnt = 0;
    }
}
