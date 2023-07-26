package com.ssafy.campfire.user.service;

import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.domain.Role;
import com.ssafy.campfire.user.dto.UserSignUpDto;
import com.ssafy.campfire.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        if (userRepository.findByNickname(userSignUpDto.getNickname()).isPresent()) {
            throw new Exception("이미 존재하는 닉네임입니다.");
        }

        User user = null;

        user = User.builder()
                .nickname(userSignUpDto.getNickname())
                .role(Role.USER)
                .build();

//        user = User.builder()
//                .userId(userSignUpDto.getUserId())
//                .password(userSignUpDto.getPassword())
//                .nickname(userSignUpDto.getNickname())
//                .role(Role.USER)
//                .build();


        //user.passwordEncode(passwordEncoder);
        userRepository.save(user);
    }

    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

//    public User getLoginUserByLoginId(String loginId) {
//        if(loginId == null) return null;
//
//        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
//        if(optionalUser.isEmpty()) return null;
//
//        return optionalUser.get();
//    }
}


