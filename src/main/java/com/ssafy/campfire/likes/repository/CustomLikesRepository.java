package com.ssafy.campfire.likes.repository;

public interface CustomLikesRepository {
    Boolean hasLikeByUserId(Long boardId, Long userId);
}
