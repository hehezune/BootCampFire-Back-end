package com.ssafy.campfire.user.service;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.repository.BootcampRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.dto.request.UserUpdateRequest;
import com.ssafy.campfire.user.dto.response.UserConfirmResponse;
import com.ssafy.campfire.user.dto.response.UserReadResponse;
import com.ssafy.campfire.user.dto.response.UserUpdateResponse;
import com.ssafy.campfire.user.repository.CustomUserRepository;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;
    private final BootcampRepository bootcampRepository;

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
    public UserReadResponse read(Long userId) {

        User user = userRepository.findById(userId)
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
    public UserUpdateResponse update(Long userId,
                                     UserUpdateRequest request) {

        User user = userRepository.findById(userId)
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

    // 소속 인증 요청하기
    public boolean confirmRequest(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        System.out.println("전 : "+user.toString());

        if(user.getIsPermision()){
            user.updatePermision(false);
        }
        System.out.println("후 : "+user.getIsPermision());
        return true;
    }

    // 소속 인증을 요청한 사용자 목록 불러오기
    public List<UserConfirmResponse> needPermissionUserList(){
        Optional<List<User>> optionalUserList = customUserRepository.findNeedPermissionUserList();

        if(optionalUserList.isEmpty()){
            return null;
        }

        List<User> userList = optionalUserList.get();
        List<UserConfirmResponse> userConfirmResponseList = new ArrayList<>();

        for (User user:userList) {
            userConfirmResponseList.add(UserConfirmResponse.from(user.getId(), user.getNickname(), user.getImgUrl()));
        }


        return userConfirmResponseList;
    }

    // 소속 인증 허가
    public User permission(Long userId, Long bootcampId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Bootcamp bootcamp = bootcampRepository.findById(bootcampId)
                .orElseThrow(()-> new BusinessException(ErrorMessage.BOOTCAMP_NOT_FOUND));
        
        user.updateBootcamp(bootcamp);
        return user;
    }

    // 소속 인증 반려
    public String notPermission(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

        user.updatePermision(true);

        return "소속 인증이 반려 되었습니다.";
    }
}


