package com.ssafy.campfire.category.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.category.repository.CustomCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.campfire.board.domain.QBoard.board;
import static com.ssafy.campfire.category.domain.QCategory.category;

@Repository
@RequiredArgsConstructor
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Board> getLatestFiveBoard(Long categoryId) {
        List<Board> fivePosts = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.createdDate.desc())
                .limit(5)
                .fetch();
        return fivePosts;
    }

    @Override
    public Page<Board> getBoardByNewest(Long categoryId, Pageable pageable) {
        List<Board> newestPosts = queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return (Page<Board>) newestPosts;
//        JPAQuery<Long> countQuery = queryFactory.select(board.count())
//                     .from(board)
//                     .leftJoin(board.user, member)
//                    .where(
//                      isShareEq(condition.isShare()),
//                    mainCategoryEq(condition.mainCategory()),
//                     midCategoryEq(condition.midCategory()),
//                     subCategoryEq(condition.subCategory()),
//                    post.id.in(reportedPostIds).not()
//                            );
//
//             return PageableExecutionUtils.getPage(posts, pageable, countQuery::fetchOne);
//        return newestPosts;
    }

    @Override
    public Page<Board> getBoardByLike(Long categoryId, Pageable pageable) {
        return (Page<Board>) queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.likeCnt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Page<Board> getBoardByView(Long categoryId, Pageable pageable) {
        return (Page<Board>) queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.view.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
