package com.ssafy.campfire.board.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.repository.CustomBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ssafy.campfire.board.domain.QBoard.board;
import static com.ssafy.campfire.bootcamp.domain.QBootcamp.bootcamp;
import static com.ssafy.campfire.category.domain.QCategory.category;
import static com.ssafy.campfire.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Board> getByIdFetchJoin(Long boardId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(board)
                        .leftJoin(board.user, user).fetchJoin()
                        .leftJoin(board.bootcamp, bootcamp).fetchJoin()
                        .leftJoin(board.category, category).fetchJoin()
                        .where(board.id.eq(boardId))
                        .fetchOne()
        );
    }
}
