package com.ssafy.campfire.user.service;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.dto.request.BoardUpdateRequest;
import com.ssafy.campfire.board.dto.response.BoardUpdateResponse;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.dto.request.UserUpdateRequest;
import com.ssafy.campfire.user.dto.response.UserReadResponse;
import com.ssafy.campfire.user.dto.response.UserUpdateResponse;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
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
    public UserReadResponse read(String nickname) {

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));


        return new UserReadResponse(
                user.getId(),
                user.getNickname(),
                user.getBojId(),
                user.getImgUrl(),
                user.getBootcamp().getId(),
                user.getBootcamp().getName(),
                user.getEmail()
        );
    }
    public UserUpdateResponse update(String nickname,
                                     UserUpdateRequest request) {

        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

        user.update(request.toDto());

        return new UserUpdateResponse(
                user.getId(),
                user.getNickname(),
                user.getBojId(),
                user.getBootcamp().getName(),
                user.getImgUrl(),
                user.getEmail()
        );
    }

    public boolean nickanameDuplicationCheck(String nickname){
        if(!userRepository.findByNickname(nickname).isEmpty()){
            return false;
        }

        return true;
    }

    public boolean confirmRequest(String nickname){
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

        if(user == null){
            return false;
        }

        if(!user.getIsPermision()){
            user.updatePermision(true);
        }

        return true;
    }
}


