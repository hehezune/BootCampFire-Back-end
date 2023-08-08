package com.ssafy.campfire.game.domain;

import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.utils.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity
@Table(name = "game") // DB 이름과 동일하게
@ToString
public class Game extends BaseEntity {
    /*
    id : pk
    user : 게임한 유저
    bestScore : 유저의 게임 최대 점수
   */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "best_score")
    private Integer bestScore;

    @Builder
    public Game(User user, Integer bestScore){
        this.user = user;
        this.bestScore = bestScore;
        this.createdDate = LocalDateTime.now();
    }

    public void updateGameScore(Integer bestScore){
        this.bestScore = bestScore;
        this.updatedDate = LocalDateTime.now();

    }





}
