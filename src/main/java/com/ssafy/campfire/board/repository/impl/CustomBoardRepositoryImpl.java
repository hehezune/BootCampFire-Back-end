package com.ssafy.campfire.board.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.dto.response.UserBoardListResponse;
import com.ssafy.campfire.board.repository.CustomBoardRepository;
import com.ssafy.campfire.category.dto.response.BoardListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    public void updateView(Long boardId) {
         queryFactory
                .update(board)
                .set(board.view, board.view.add(1))
                .where(board.id.eq(boardId))
                .execute();
    }


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

    /**
     * slice
     */
    @Override
    public Slice<UserBoardListResponse> getUserBoard(Long userId, Pageable pageable) {

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

        List<UserBoardListResponse> content = new ArrayList<>();
        for (Board board: boards) {
            UserBoardListResponse item = UserBoardListResponse.of(board);
            content.add(item);
        }

        boolean hasNext = false;
        if(content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }

    /**
     * page
     */
//    @Override
//    public Page<Board> getUserBoard(Long userId, Pageable pageable) {
//
//        List<Board> boards = queryFactory.select(board)
//                .from(board)
//                .leftJoin(board.category, category)
//                .fetchJoin()
//                .leftJoin(board.user, user)
//                .fetchJoin()
//                .where(board.user.id.eq(userId))
//                .orderBy(board.createdDate.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        JPAQuery<Long> countQuery = queryFactory.select(board.count())
//                .from(board)
//                .leftJoin(board.category, category)
//                .fetchJoin()
//                .leftJoin(board.user, user)
//                .fetchJoin()
//                .where(board.user.id.eq(userId));
//
//        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
//    }
}
