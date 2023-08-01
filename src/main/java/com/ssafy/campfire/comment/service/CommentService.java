package com.ssafy.campfire.comment.service;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.repository.BoardRepository;
import com.ssafy.campfire.comment.domain.Comment;
import com.ssafy.campfire.comment.dto.request.CommentCreateRequest;
import com.ssafy.campfire.comment.dto.request.CommentUpdateRequest;
import com.ssafy.campfire.comment.dto.response.CommentCreateResponse;
import com.ssafy.campfire.comment.dto.response.CommentReadResponse;
import com.ssafy.campfire.comment.dto.response.CommentUpdateResponse;
import com.ssafy.campfire.comment.repository.CommentRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        if(request.preCommentId()!=null){
            Comment preComment = commentRepository.findById(request.preCommentId())
                    .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));
            comment.setOrder(preComment.getRef(), preComment.getMaxRefOrder());
            preComment.addMaxRefOrder();
        } else{
            comment.setOrder(board.getMaxRef()+1, 0);
            comment.addMaxRefOrder();
            board.addMaxRef();
        }
        comment.writeBy(user, board);
        board.addCommentCnt();
        Comment savedComment = commentRepository.save(comment);

        return CommentCreateResponse.from(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentReadResponse> getCommentList(Long commentId){
        List<CommentReadResponse> commentReadResponses = commentRepository
                .getCommentList(commentId)
                .stream()
                .map(CommentReadResponse::of)
                .toList();
        return commentReadResponses;
    }


    public CommentUpdateResponse update(Long commentId, CommentUpdateRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));

        comment.update(request.toDto());

        return new CommentUpdateResponse(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getNickname(),
                comment.getContent(),
                comment.getAnonymous(),
                comment.getRef(),
                comment.getRefOrder()
        );
    }

    public Long delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.COMMENT_NOT_FOUND));

        Long boardId = comment.getBoard().getId();
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));
        board.minusCommentCnt();

        commentRepository.delete(comment);
        return comment.getId();
    }
}
