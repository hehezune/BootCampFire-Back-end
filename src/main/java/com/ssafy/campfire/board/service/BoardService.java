package com.ssafy.campfire.board.service;

import com.ssafy.campfire.board.domain.Board;
import com.ssafy.campfire.board.dto.request.BoardCreateRequest;
import com.ssafy.campfire.board.dto.request.BoardUpdateRequest;
import com.ssafy.campfire.board.dto.response.BoardCreateResponse;
import com.ssafy.campfire.board.dto.response.BoardReadResponse;
import com.ssafy.campfire.board.dto.response.BoardUpdateResponse;
import com.ssafy.campfire.board.repository.BoardRepository;
import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.category.dto.response.BoardListResponse;
import com.ssafy.campfire.category.repository.CategoryRepository;
import com.ssafy.campfire.likes.repository.LikesRepository;
import com.ssafy.campfire.user.domain.User;
import com.ssafy.campfire.user.repository.UserRepository;
import com.ssafy.campfire.utils.dto.response.GlobalPageResponseDto;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final LikesRepository likesRepository;

    public BoardCreateResponse save(Long userId, BoardCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));

        Board board = request.toEntity();
        board.setCategory(user, category);
        board.writeBy(user);
        Board savedPost = boardRepository.save(board);

        return BoardCreateResponse.from(savedPost);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getUserBoard(Long userId, Pageable pageable){

        Page<BoardListResponse> page = boardRepository
                .getUserBoard(userId, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public BoardReadResponse get(Long boardId, Long userId){
        Board board = boardRepository.getByIdFetchJoin(boardId)
                .orElseThrow(() -> new BusinessException((ErrorMessage.BOARD_NOT_FOUND)));

        Boolean hasLike = false;

        if(userId != null){
            hasLike = likesRepository.hasLikeByUserId(boardId, userId);
        }

        return BoardReadResponse.from(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getBootcamp().getName(),
                board.getUser().getNickname(),
                board.getAnonymous(),
                board.getCommentCnt(),
                board.getLikeCnt(),
                board.getView(),
                hasLike,
                board.getCreatedDate()
        );
    }

    public BoardUpdateResponse update(Long boardId,
                                      BoardUpdateRequest request) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));

        board.update(request.toDto());

        return new BoardUpdateResponse(
                board.getId(),
                board.getCategory().getName().getMessage(),
                board.getUser().getNickname(),
                board.getTitle(),
                board.getContent(),
                board.getAnonymous()
        );
    }

    public Long delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.BOARD_NOT_FOUND));
        boardRepository.delete(board);
        return board.getId();
    }

}
