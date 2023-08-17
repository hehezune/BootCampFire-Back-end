package com.ssafy.campfire.comment.repository;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
    List<Comment> getCommentsByRefAndBoard(int ref, Board board);

}
