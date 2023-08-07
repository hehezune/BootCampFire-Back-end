package com.ssafy.campfire.likes.service;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.repository.BoardRepository;
import com.ssafy.campfire.likes.dto.request.LikesAddRequest;
import com.ssafy.campfire.likes.dto.response.LikesResponse;
import com.ssafy.campfire.likes.repository.LikesRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public LikesResponse createLikes(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));

        boolean isAlreadyLike = likesRepository.findByUserAndBoard(user, board).isPresent();

        if (!isAlreadyLike) {
            LikesAddRequest likeAddRequest = new LikesAddRequest(user, board);
            likesRepository.save(likeAddRequest.toEntity());
            board.addLikeCnt();
        }

        return LikesResponse.from(board);
    }

    public LikesResponse cancelLikes(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));

        likesRepository.findByUserAndBoard(user, board)
                .ifPresent(it -> {
                    likesRepository.delete(it);
                    board.minusLikes(it);
                    board.minusLikeCnt();
                });

        return LikesResponse.from(board);
    }

}