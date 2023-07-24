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

//    //user, likes, comment join 필요
    @Override
    public List<Board> getLatestFiveBoard(Long categoryId) {
        return queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .where(board.category.id.eq(categoryId))
                .orderBy(board.createdDate.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public Page<Board> getBoardByNewest(Long categoryId, Pageable pageable) {
        return (Page<Board>) queryFactory.select(board)
                .from(board)
                .leftJoin(board.category, category)
                .fetchJoin()
                .where(board.category.id.eq(categoryId))
                .orderBy(board.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
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
