package com.ssafy.campfire.user.service;

import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.domain.Role;
import com.ssafy.campfire.user.dto.UserSignUpDto;
import com.ssafy.campfire.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void signUp(UserSignUpDto userSignUpDto) throws Exception {

        String provider = userSignUpDto.getProvider();

        if(provider.equals("kakao")){
            if (userRepository.findByKakaoEmail(userSignUpDto.getEmail()).isPresent()) {
                throw new Exception("이미 존재하는 이메일입니다.");
            }
        } else if (provider.equals("google")) {
            if (userRepository.findByGoogleEmail(userSignUpDto.getEmail()).isPresent()) {
                throw new Exception("이미 존재하는 이메일입니다.");
            }
        } else if (provider.equals("naver")) {
            if (userRepository.findByNaverEmail(userSignUpDto.getEmail()).isPresent()) {
                throw new Exception("이미 존재하는 이메일입니다.");
            }
        }

        User user = null;

        if(provider.equals("kakao")){
            user = User.builder()
                    .kakaoEmail(userSignUpDto.getEmail())
                    .nickname(userSignUpDto.getNickname())
                    .role(Role.USER)
                    .build();
        } else if (provider.equals("google")) {
            user = User.builder()
                    .googleEmail(userSignUpDto.getEmail())
                    .nickname(userSignUpDto.getNickname())
                    .role(Role.USER)
                    .build();
        } else if (provider.equals("naver")) {
            user = User.builder()
                    .naverEmail(userSignUpDto.getEmail())
                    .nickname(userSignUpDto.getNickname())
                    .role(Role.USER)
                    .build();
        }


        userRepository.save(user);
    }
}


