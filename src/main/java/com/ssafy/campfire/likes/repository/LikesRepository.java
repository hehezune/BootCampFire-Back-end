package com.ssafy.campfire.likes.repository;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.likes.domain.Likes;
import com.ssafy.campfire.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long>, CustomLikesRepository {
    Optional<Likes> findByUserAndBoard(User user, Board board);
    List<Likes> findAllByUser(User user);
}
