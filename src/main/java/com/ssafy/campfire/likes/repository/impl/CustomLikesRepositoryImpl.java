package com.ssafy.campfire.likes.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.likes.repository.CustomLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.campfire.likes.domain.QLikes.likes;

@Repository
@RequiredArgsConstructor
public class CustomLikesRepositoryImpl implements CustomLikesRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean hasLikeByUserId(Long boardId, Long userId) {
        return queryFactory.selectFrom(likes)
                .where(likes.user.id.eq(userId), likes.board.id.eq(boardId))
                .fetchOne() != null;
    }

}
