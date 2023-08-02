package com.ssafy.campfire.algorithm.domain;

import com.ssafy.campfire.utils.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity
@Table(name = "algorithm") // DB 이름과 동일하게
public class Algorithm extends BaseEntity {
    /*
    id : pk
    num : 문제번호
    link : 문제 링크
    title: 문제 제목
    description : 문제 내용
    date : 문제 제출 날짜
     */
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long num;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private LocalDate date;
    @Builder
    public Algorithm(Long num,LocalDate date){
        this.num = num;
        this.date = date;
        this.createdDate = LocalDateTime.now();
    }

    public void setAlgorithmContents(String link, String title, String description){
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public void updateAlgorithm(Long num,LocalDate date){
        this.num = num;
        this.date = date;
        this.updatedDate = LocalDateTime.now();
    }


}
