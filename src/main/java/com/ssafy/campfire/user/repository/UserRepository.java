package com.ssafy.campfire.user.repository;


import com.ssafy.campfire.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByKakaoEmail(String kakaoEmail);
    Optional<User> findByGoogleEmail(String googleEmail);
    Optional<User> findByNaverEmail(String NaverEmail);

    Optional<User> findByNickname(String nickname);

    Optional<User> findByRefreshToken(String refreshToken);
}
