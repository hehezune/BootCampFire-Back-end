package com.ssafy.campfire.category.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.category.repository.CustomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.ssafy.campfire.board.domain.QBoard.board;
import static com.ssafy.campfire.bootcamp.domain.QBootcamp.bootcamp;
import static com.ssafy.campfire.category.domain.QCategory.category;
import static com.ssafy.campfire.category.domain.enums.CategoryType.BOOTCAMP;
import static com.ssafy.campfire.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> getHotFiveBoard() {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(
                        board.category.name.ne(BOOTCAMP),
                        board.createdDate.between(LocalDateTime.now().minusDays(3), LocalDateTime.now())
                        )
                .orderBy(board.createdDate.desc())
                .orderBy(board.likeCnt.desc())
                .limit(10)
                .fetch();

        return boards;
    }

    @Override
    public List<Board> getLatestFiveBoard(Long categoryId) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.createdDate.desc())
                .limit(5)
                .fetch();

        return boards;
    }

    @Override
    public Page<Board> getBoardByNewest(Long categoryId, Pageable pageable) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
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
                .where(board.category.id.eq(categoryId));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBootBoardByNewest(Long categoryId, Long bootcampId, Pageable pageable) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.category.id.eq(categoryId)
                        )
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
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBoardByLike(Long categoryId, Pageable pageable) {

        List<Board> boards =  queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.likeCnt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(board.count())
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.category.id.eq(categoryId));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBootBoardByLike(Long categoryId, Long bootcampId, Pageable pageable) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.category.id.eq(categoryId)
                )
                .orderBy(board.likeCnt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(board.count())
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBoardByView(Long categoryId, Pageable pageable) {

        List<Board> boards =  queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.view.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(board.count())
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(board.category.id.eq(categoryId));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBootBoardByView(Long categoryId, Long bootcampId, Pageable pageable) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.category.id.eq(categoryId)
                )
                .orderBy(board.view.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(board.count())
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getMainSearchByTitleContent(String keyword, Pageable pageable) {

        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where((board.title.contains(keyword)).or(board.content.contains(keyword)))
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
                .where((board.title.contains(keyword)).or(board.content.contains(keyword)));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getMainSearchByNickname(String nickname, Pageable pageable) {
        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(
                        board.anonymous.eq(false),
                        board.user.nickname.contains(nickname)
                )
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
                .where(board.user.nickname.contains(nickname));

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getSearchByTitleContent(Long categoryId, String keyword, Pageable pageable) {
        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(
                        (board.title.contains(keyword)).or(board.content.contains(keyword)),
                        board.category.id.eq(categoryId)
                )
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
                .where(
                        (board.title.contains(keyword)).or(board.content.contains(keyword)),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getSearchByNickname(Long categoryId, String nickname, Pageable pageable) {
        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .where(
                        board.anonymous.eq(false),
                        board.user.nickname.contains(nickname),
                        board.category.id.eq(categoryId)
                )
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
                .where(
                        board.user.nickname.contains(nickname),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBootSearchByTitleContent(Long categoryId, Long bootcampId, String keyword, Pageable pageable) {
        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        (board.title.contains(keyword)).or(board.content.contains(keyword)),
                        board.category.id.eq(categoryId)
                )
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
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        (board.title.contains(keyword)).or(board.content.contains(keyword)),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<Board> getBootSearchByNickname(Long categoryId, Long bootcampId, String nickname, Pageable pageable) {
        List<Board> boards = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .leftJoin(board.user, user)
                .fetchJoin()
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.user.nickname.contains(nickname),
                        board.category.id.eq(categoryId)
                )
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
                .leftJoin(board.category.bootcamp, bootcamp)
                .fetchJoin()
                .where(
                        board.category.bootcamp.id.eq(bootcampId),
                        board.user.nickname.contains(nickname),
                        board.category.id.eq(categoryId)
                );

        return PageableExecutionUtils.getPage(boards, pageable, countQuery::fetchOne);
    }
}
