package com.ssafy.campfire.user.controller;

import com.ssafy.campfire.board.dto.request.BoardUpdateRequest;
import com.ssafy.campfire.board.dto.response.BoardUpdateResponse;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.dto.request.UserUpdateRequest;
import com.ssafy.campfire.user.dto.response.UserReadResponse;
import com.ssafy.campfire.user.dto.response.UserUpdateResponse;
import com.ssafy.campfire.user.service.UserService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @ApiOperation(value ="개인 정보 조회")
    @GetMapping("")
    public BaseResponseDto<UserReadResponse> userInfo(Authentication auth) {
        return BaseResponseDto.ok(userService.read(auth.getName()));
    }

    @ApiOperation(value = "개인 정보 수정")
    @PutMapping("")
    public BaseResponseDto<UserUpdateResponse> update(Authentication auth, @RequestBody @Valid UserUpdateRequest request){
        return BaseResponseDto.ok(userService.update(auth.getName(), request));
    }

    @ApiOperation("닉네임 중복 검사")
    @PostMapping("/duplication")
    public BaseResponseDto<Boolean> duplicationCheck(@RequestBody @Valid String nickname){
        return BaseResponseDto.ok(userService.nickanameDuplicationCheck(nickname));
    }
    @ApiOperation("소속 인증 확인 요청")
    @PostMapping("/confirm")
    public BaseResponseDto<Boolean> confirm(Authentication auth){
        return BaseResponseDto.ok(userService.confirmRequest(auth.getName()));
    }
}
