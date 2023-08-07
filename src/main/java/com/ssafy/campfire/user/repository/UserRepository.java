package com.ssafy.campfire.user.repository;


import com.ssafy.campfire.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);
    Optional<User> findByEmail(String Email);
    Optional<User> findByNickname(String nickname);
    User findDeatailByEmail(String email);
    Optional<User> findByRefreshToken(String refreshToken);

    Optional<User> findByEmailAndProvider(String email, String provider);

    User findUserById(Long userId);
}
