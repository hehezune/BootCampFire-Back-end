package com.ssafy.campfire.game.dto.response;

import com.ssafy.campfire.game.domain.Game;
import lombok.Builder;

@Builder
public record GameRankResponseDto(
        String userNickname,
        String bootcampName,
        Integer score,
        Integer rank
) {
    public  static GameRankResponseDto of(Game game, Integer rank){
        return GameRankResponseDto.builder()
                .userNickname(game.getUser().getNickname())
                .bootcampName(game.getUser().getBootcamp().getName())
                .score(game.getBestScore())
                .rank(rank)
                .build();

    }
}
