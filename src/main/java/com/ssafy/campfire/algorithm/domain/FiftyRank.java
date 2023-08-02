package com.ssafy.campfire.algorithm.domain;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.utils.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "fifty_rank")
public class FiftyRank extends BaseEntity {
    /*
    id : pk
    bootcamp : 부트캠프
    algorithm : 알고리즘 문제
    rank : 순위
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    Bootcamp bootcamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "algorithm_id")
    Algorithm algorithm;

    Integer rank;

    @Builder
    public FiftyRank(Bootcamp bootcamp , Algorithm algorithm, Integer rank){
        this.bootcamp = bootcamp;
        this.algorithm = algorithm;
        this.rank = rank;
        this.createdDate = LocalDateTime.now();
    }

}
