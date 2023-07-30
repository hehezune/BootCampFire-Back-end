package com.ssafy.campfire.user.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.campfire.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<User>> findNeedPermissionUserList() {
        return Optional.ofNullable(
                queryFactory
                        .select(user)
                        .from(user)
                        .where(user.isPermision.eq(false))
                        .fetch()
        );
    }
}
