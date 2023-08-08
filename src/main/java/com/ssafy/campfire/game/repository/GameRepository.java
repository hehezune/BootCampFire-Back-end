package com.ssafy.campfire.game.repository;

import com.ssafy.campfire.game.domain.Game;
import com.ssafy.campfire.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findByUser(User user);

    List<Game> findTop10ByOrderByBestScoreDesc();

    Long countAllByBestScoreGreaterThanEqual(Integer bestScore);

}
