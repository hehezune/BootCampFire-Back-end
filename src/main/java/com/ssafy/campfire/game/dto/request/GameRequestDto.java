package com.ssafy.campfire.game.dto.request;

import com.ssafy.campfire.game.domain.Game;
import com.ssafy.campfire.user.domain.User;

public record GameRequestDto(
        Integer bestScore
) {
    public Game toGame(User user){
        return new Game(user, bestScore);
    }
}
