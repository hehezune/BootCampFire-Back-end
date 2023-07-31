package com.ssafy.campfire.comment.service;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.repository.BoardRepository;
import com.ssafy.campfire.comment.domain.Comment;
import com.ssafy.campfire.comment.dto.request.CommentCreateRequest;
import com.ssafy.campfire.comment.dto.response.CommentCreateResponse;
import com.ssafy.campfire.comment.repository.CommentRepository;
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
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentCreateResponse save(Long userId, CommentCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Board board = boardRepository.findById(request.boardId())
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));

        Comment comment = request.toEntity();

        if(request.ref()!=null){
            comment.setOrder(request.ref(), request.refOrder()+1);
        } else{
            comment.setOrder(board.getMaxRef()+1, 0);
            board.addMaxRef();
        }
        comment.writeBy(user, board);
        board.addCommentCnt();
        Comment savedComment = commentRepository.save(comment);

        return CommentCreateResponse.from(savedComment);
    }
}
