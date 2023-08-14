package com.ssafy.campfire.game.service;

import com.ssafy.campfire.game.domain.Game;
import com.ssafy.campfire.game.dto.request.GameRequestDto;
import com.ssafy.campfire.game.dto.response.GameRankResponseDto;
import com.ssafy.campfire.game.repository.GameRepository;
import com.ssafy.campfire.global.oauth2.PrincipalDetails;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public GameRankResponseDto getMyRank(PrincipalDetails loginUser) {
       User user = userRepository.findUserById(loginUser.getId());
        // User user = userRepository.findUserById(1L);
        Game game = gameRepository.findByUser(user);
        Long rank;
        if(game == null){
            game = new Game(user, -1);
            rank = 0L;
        }else {
             rank = gameRepository.countAllByBestScoreGreaterThanEqual(game.getBestScore());
        }
        return GameRankResponseDto.of(game, Math.toIntExact(rank));
    }

    public List<GameRankResponseDto> getGameRank() {
        List<GameRankResponseDto> gameRankResponseDtoList = new ArrayList<>();

        List<Game> gameList = gameRepository.findTop10ByOrderByBestScoreDesc();

        Integer rank = 1;
        for (Game game : gameList) {
            gameRankResponseDtoList.add(GameRankResponseDto.of(game, rank));
            rank += 1;
        }


        return gameRankResponseDtoList;
    }

    public GameRankResponseDto saveScore(GameRequestDto gameRequestDto, PrincipalDetails loginUser) {
       User user = userRepository.findUserById(loginUser.getId());
        // User user = userRepository.findUserById(1L);

        Game game = gameRepository.findByUser(user);

        if(game == null){ //사용자의 게임 점수가 없으면
            //새로 만들어서 추가하고
            game = gameRequestDto.toGame(user);
            gameRepository.save(game);
        }else{
            //있으면 점수 수정만 한다.
            game.updateGameScore(gameRequestDto.bestScore());
        }

        return getMyRank(loginUser);
    }
}
