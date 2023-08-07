package com.ssafy.campfire.comment.repository;

import com.ssafy.campfire.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CustomCommentRepository {
}
