package com.ssafy.campfire.comment.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.comment.domain.Comment;
import com.ssafy.campfire.comment.repository.CustomCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.campfire.board.domain.QBoard.board;
import static com.ssafy.campfire.comment.domain.QComment.comment;
import static com.ssafy.campfire.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomCommentRepositoryImpl implements CustomCommentRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comment> getCommentList(Long boardId) {

        List<Comment> comments = queryFactory.select(comment)
                .from(comment)
                .leftJoin(comment.board, board)
                .fetchJoin()
                .leftJoin(comment.user, user)
                .fetchJoin()
                .where(
                        comment.board.id.eq(boardId)
                )
                .orderBy(comment.ref.asc())
                .orderBy(comment.refOrder.asc())
                .fetch();

        return comments;
    }
}
