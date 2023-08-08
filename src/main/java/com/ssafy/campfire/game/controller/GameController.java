package com.ssafy.campfire.game.controller;

import com.ssafy.campfire.game.dto.request.GameRequestDto;
import com.ssafy.campfire.game.dto.response.GameRankResponseDto;
import com.ssafy.campfire.game.service.GameService;
import com.ssafy.campfire.global.login.PrincipalDetails;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @ApiOperation(value ="내 랭크 조회")
    @GetMapping("/my-rank")
    public BaseResponseDto<GameRankResponseDto> getMyRank(@AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok( gameService.getMyRank(user));
    }

    @ApiOperation(value ="전체 게임 랭크 조회")
    @GetMapping
    public BaseResponseDto<List<GameRankResponseDto>> getGameRank(){
        return BaseResponseDto.ok( gameService.getGameRank());
    }

    @ApiOperation(value ="내 점수 등록")
    @PostMapping
    public BaseResponseDto<GameRankResponseDto> saveScore(@RequestBody GameRequestDto gameRequestDto,
                                                            @AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok(gameService.saveScore(gameRequestDto, user));
    }

    @ApiOperation(value ="내 점수 갱신")
    @PutMapping
    public BaseResponseDto<GameRankResponseDto> updateScore(@RequestBody GameRequestDto gameRequestDto,
                                                            @AuthenticationPrincipal PrincipalDetails user){
        return BaseResponseDto.ok( gameService.updateScore(gameRequestDto, user));
    }



}
