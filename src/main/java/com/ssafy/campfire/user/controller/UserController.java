package com.ssafy.campfire.user.controller;

import com.ssafy.campfire.board.dto.request.BoardUpdateRequest;
import com.ssafy.campfire.board.dto.response.BoardUpdateResponse;
import com.ssafy.campfire.global.login.PrincipalDetails;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.dto.request.UserUpdateRequest;
import com.ssafy.campfire.user.dto.response.UserConfirmResponse;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @ApiOperation(value ="개인 정보 조회")
    @GetMapping("")
    public BaseResponseDto<UserReadResponse> userInfo(@AuthenticationPrincipal PrincipalDetails user) {
        return BaseResponseDto.ok(userService.read(user.getName()));
    }

    @ApiOperation(value = "개인 정보 수정")
    @PutMapping("")
    public BaseResponseDto<UserUpdateResponse> update(@AuthenticationPrincipal PrincipalDetails user, @RequestBody @Valid UserUpdateRequest request){
        return BaseResponseDto.ok(userService.update(user.getName(), request));
    }

    @ApiOperation("닉네임 중복 검사")
    @PostMapping("/duplication")
    public BaseResponseDto<String> duplicationCheck(@RequestBody @Valid String nickname){
        boolean state = userService.nickanameDuplicationCheck(nickname);
        if(!state){
            return BaseResponseDto.message(nickname,"이미 사용 중인 닉네임입니다.");
        }
        return BaseResponseDto.ok(nickname);
    }

    @ApiOperation("소속 인증 요청하기")
    @PostMapping("/confirm")
    public BaseResponseDto<Boolean> confirmRequest(@AuthenticationPrincipal PrincipalDetails user){
        boolean state = userService.confirmRequest(3L);
        return BaseResponseDto.ok(state);
    }

    @ApiOperation("소속 인증 요청 리스트 받아오기 - 관리자")
    @GetMapping("/admin/permission/list")
    public BaseResponseDto<List<UserConfirmResponse>> permissionList(){
        return BaseResponseDto.ok(userService.needPermissionUserList());
    }

    @ApiOperation("소속 인증 허가 - 관리자")
    @PutMapping("/admin/permission/{userId}")
    public BaseResponseDto<User> permission(@PathVariable Long userId, @RequestBody @Valid Long bootcampId){
        return BaseResponseDto.ok(userService.permission(userId, bootcampId));
    }

    @ApiOperation("소속 인증 반려 - 관리자")
    @PutMapping("/admin/reject/{userId}")
    public BaseResponseDto<String> notPermission(@PathVariable Long userId){
        return BaseResponseDto.ok(userService.notPermission(userId));
    }
}
