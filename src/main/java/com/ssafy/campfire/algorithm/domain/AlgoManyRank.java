package com.ssafy.campfire.algorithm.domain;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "algo_many_rank")
public class AlgoManyRank extends BaseEntity {
    /*
    id : pk
    user : 사용자
    bootcamp : 부트캠프
    algorithm: 알고리즘
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bootcamp_id")
    private Bootcamp bootcamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "algorithm_id")
    private Algorithm algorithm;


    @Builder
    public AlgoManyRank(User user, Bootcamp bootcamp, Algorithm algorithm){
        this.user = user;
        this.bootcamp = bootcamp;
        this.algorithm = algorithm;
        this.createdDate = LocalDateTime.now();
    }
}
