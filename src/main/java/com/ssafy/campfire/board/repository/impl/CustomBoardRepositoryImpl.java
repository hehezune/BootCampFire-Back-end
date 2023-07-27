package com.ssafy.campfire.board.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.repository.CustomBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    @Override
    public Page<Board> getUserBoard(Long userId, Pageable pageable) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.user.id.eq(userId))
                .orderBy(board.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(board.count())
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.user.id.eq(userId));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }
}
