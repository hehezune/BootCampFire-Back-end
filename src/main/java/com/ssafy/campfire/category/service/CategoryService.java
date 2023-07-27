package com.ssafy.campfire.category.service;

import com.ssafy.campfire.category.domain.Category;
import com.ssafy.campfire.category.dto.response.BoardHotResponse;
import com.ssafy.campfire.category.dto.response.BoardListResponse;
import com.ssafy.campfire.category.dto.response.BoardMainResponse;
import com.ssafy.campfire.category.repository.CategoryRepository;
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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<BoardMainResponse> getMainList(Long categoryId){
        List<BoardMainResponse> boardMainResponses = (List<BoardMainResponse>) categoryRepository
                .getLatestFiveBoard(categoryId)
                .stream()
                .map(BoardMainResponse::of);
        return boardMainResponses;
    }

    @Transactional(readOnly = true)
    public List<BoardHotResponse> getHotList(){
        List<BoardHotResponse> boardHotResponses = (List<BoardHotResponse>) categoryRepository
                .getHotFiveBoard()
                .stream()
                .map(BoardHotResponse::of);
        return boardHotResponses;
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getNewestList(Long userId, Long categoryId, Pageable pageable){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));

        if( userId != null && category.getName().getMessage().equals("부트캠프")){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Page<BoardListResponse> page = categoryRepository
                    .getBootBoardByNewest(categoryId, user.getBootcamp().getId(), pageable)
                    .map(BoardListResponse::of);

            return GlobalPageResponseDto.of(page);
        }

        Page<BoardListResponse> page = categoryRepository
                .getBoardByNewest(categoryId, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getLikeOrderList(Long userId, Long categoryId, Pageable pageable){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));

        if(userId != null && category.getName().getMessage().equals("부트캠프")){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Page<BoardListResponse> page = categoryRepository
                    .getBootBoardByLike(categoryId, user.getBootcamp().getId(), pageable)
                    .map(BoardListResponse::of);

            return GlobalPageResponseDto.of(page);
        }

        Page<BoardListResponse> page = categoryRepository
                .getBoardByLike(categoryId, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getViewOrderList(Long userId, Long categoryId, Pageable pageable){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));

        if( userId != null && category.getName().getMessage().equals("부트캠프")){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Page<BoardListResponse> page = categoryRepository
                    .getBootBoardByView(categoryId, user.getBootcamp().getId(), pageable)
                    .map(BoardListResponse::of);

            return GlobalPageResponseDto.of(page);
        }

        Page<BoardListResponse> page = categoryRepository
                .getBoardByView(categoryId, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getMainSearchTitleContentList(String keyword, Pageable pageable){

        Page<BoardListResponse> page = categoryRepository
                .getMainSearchByTitleContent(keyword, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getMainSearchNicknameList(String nickname, Pageable pageable){

        Page<BoardListResponse> page = categoryRepository
                .getMainSearchByNickname(nickname, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getSearchByTitleContent(Long userId, Long categoryId, String keyword, Pageable pageable){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));

        if( userId != null && category.getName().getMessage().equals("부트캠프")){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Page<BoardListResponse> page = categoryRepository
                    .getBootSearchByTitleContent(categoryId, user.getBootcamp().getId(), keyword, pageable)
                    .map(BoardListResponse::of);

            return GlobalPageResponseDto.of(page);
        }

        Page<BoardListResponse> page = categoryRepository
                .getSearchByTitleContent(categoryId, keyword, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }

    @Transactional(readOnly = true)
    public GlobalPageResponseDto<BoardListResponse> getSearchByNickname(Long userId, Long categoryId, String nickname, Pageable pageable){

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));

        if( userId != null && category.getName().getMessage().equals("부트캠프")){
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Page<BoardListResponse> page = categoryRepository
                    .getBootSearchByNickname(categoryId, user.getBootcamp().getId(), nickname, pageable)
                    .map(BoardListResponse::of);

            return GlobalPageResponseDto.of(page);
        }

        Page<BoardListResponse> page = categoryRepository
                .getSearchByNickname(categoryId, nickname, pageable)
                .map(BoardListResponse::of);

        return GlobalPageResponseDto.of(page);
    }
}
