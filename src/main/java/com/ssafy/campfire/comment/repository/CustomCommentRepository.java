package com.ssafy.campfire.comment.repository;

import com.ssafy.campfire.comment.domain.Comment;

import java.util.List;

public interface CustomCommentRepository {
    List<Comment> getCommentList(Long boardId);
}
