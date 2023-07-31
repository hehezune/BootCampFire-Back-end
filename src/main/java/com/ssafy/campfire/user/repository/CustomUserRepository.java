package com.ssafy.campfire.user.repository;

import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.dto.response.UserConfirmResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {
    Optional<List<User>> findNeedPermissionUserList();
}
