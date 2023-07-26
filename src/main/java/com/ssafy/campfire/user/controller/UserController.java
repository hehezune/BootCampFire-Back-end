package com.ssafy.campfire.user.controller;

import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.dto.UserSignUpDto;
import com.ssafy.campfire.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value ="로그인")
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/info")
    //@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public String userInfo(Model model, Authentication auth) {

        User loginUser = userService.getLoginUserByNickname(auth.getName());
        model.addAttribute("user", loginUser);
        System.out.println(loginUser.toString());
        return "info";
    }
}
