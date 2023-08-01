package com.ssafy.campfire.user.repository;

import com.ssafy.campfire.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    Optional<List<User>> findNeedPermissionUserList();
    //Optional<User> findByEmailAndProvider(String email, String provider);
}
