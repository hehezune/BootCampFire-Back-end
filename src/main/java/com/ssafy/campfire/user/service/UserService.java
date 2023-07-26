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

    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }

    public User getLoginUserByNickname(String nickname) {
        Optional<User> optionalUser = userRepository.findByNickname(nickname);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}


