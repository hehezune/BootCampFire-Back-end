package com.ssafy.campfire.user.controller;

import com.ssafy.campfire.global.jwt.service.JwtService;
import com.ssafy.campfire.global.oauth2.OAuthAttributes;
import com.ssafy.campfire.global.oauth2.PrincipalDetails;
import com.ssafy.campfire.user.dto.response.UserReadResponse;
import com.ssafy.campfire.user.service.UserService;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest success";
    }

    @ApiOperation(value ="개인 정보 조회")
    @GetMapping("/users")
    public BaseResponseDto<UserReadResponse> userInfo(@AuthenticationPrincipal UserDetails user, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("USER 정보 : "+principalDetails.getId());
        return BaseResponseDto.ok(userService.read(user.getUsername()));
    }


}
