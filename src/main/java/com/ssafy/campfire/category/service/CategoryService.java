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
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Long getUserBootCampCategoryId(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
        Category category = categoryRepository.findCategoryByBootcamp(user.getBootcamp())
                .orElseThrow(() -> new BusinessException(ErrorMessage.CATEGORY_NOT_FOUND));
        return category.getId();
    }

    @Transactional(readOnly = true)
    public List<BoardMainResponse> getMainList(Long categoryId){
        List<BoardMainResponse> boardMainResponses = categoryRepository
                .getLatestFiveBoard(categoryId)
                .stream()
                .map(BoardMainResponse::of).toList();
        return boardMainResponses;
    }

    @Transactional(readOnly = true)
    public List<BoardHotResponse> getHotList(){
        List<BoardHotResponse> boardHotResponses = categoryRepository
                .getHotFiveBoard()
                .stream()
                .map(BoardHotResponse::of).toList();
        return boardHotResponses;
    }

    /**
     * Slice
     */
    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getNewestList(Long userId, Long categoryId, Pageable pageable){

        if(categoryId==9){
            categoryId = getUserBootCampCategoryId(userId);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Slice<BoardListResponse> slice = categoryRepository
                    .getBootBoardByNewest(categoryId, user.getBootcamp().getId(), pageable);

            return slice;
        }

        Slice<BoardListResponse> slice = categoryRepository
                .getBoardByNewest(categoryId, pageable);

        return slice;
    }

    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getLikeOrderList(Long userId, Long categoryId, Pageable pageable){

        if(categoryId==9){
            categoryId = getUserBootCampCategoryId(userId);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Slice<BoardListResponse> slice = categoryRepository
                    .getBootBoardByLike(categoryId, user.getBootcamp().getId(), pageable);

            return slice;
        }

        Slice<BoardListResponse> slice = categoryRepository
                .getBoardByLike(categoryId, pageable);

        return slice;
    }

    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getViewOrderList(Long userId, Long categoryId, Pageable pageable){

        if(categoryId==9){
            categoryId = getUserBootCampCategoryId(userId);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Slice<BoardListResponse> slice = categoryRepository
                    .getBootBoardByView(categoryId, user.getBootcamp().getId(), pageable);

            return slice;
        }

        Slice<BoardListResponse> slice = categoryRepository
                .getBoardByView(categoryId, pageable);

        return slice;
    }

    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getMainSearchTitleContentList(String keyword, Pageable pageable){

        Slice<BoardListResponse> slice = categoryRepository
                .getMainSearchByTitleContent(keyword, pageable);

        return slice;
    }

    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getMainSearchNicknameList(String nickname, Pageable pageable){

        Slice<BoardListResponse> slice = categoryRepository
                .getMainSearchByNickname(nickname, pageable);

        return slice;
    }

    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getSearchByTitleContent(Long userId, Long categoryId, String keyword, Pageable pageable){

        if(categoryId==9){
            categoryId = getUserBootCampCategoryId(userId);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Slice<BoardListResponse> slice = categoryRepository
                    .getBootSearchByTitleContent(categoryId, user.getBootcamp().getId(), keyword, pageable);

            return slice;
        }

        Slice<BoardListResponse> slice = categoryRepository
                .getSearchByTitleContent(categoryId, keyword, pageable);

        return slice;
    }

    @Transactional(readOnly = true)
    public Slice<BoardListResponse> getSearchByNickname(Long userId, Long categoryId, String nickname, Pageable pageable){

        if(categoryId==9){
            categoryId = getUserBootCampCategoryId(userId);

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));

            Slice<BoardListResponse> slice = categoryRepository
                    .getBootSearchByNickname(categoryId, user.getBootcamp().getId(), nickname, pageable);

            return slice;
        }

        Slice<BoardListResponse> slice = categoryRepository
                .getSearchByNickname(categoryId, nickname, pageable);

        return slice;
    }

    /**
     * page
     */
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getNewestList(Long userId, Long categoryId, Pageable pageable){
//
//        if(categoryId==9){
//            categoryId = getUserBootCampCategoryId(userId);
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
//
//            Page<BoardListResponse> page = categoryRepository
//                    .getBootBoardByNewest(categoryId, user.getBootcamp().getId(), pageable)
//                    .map(BoardListResponse::of);
//
//            return GlobalPageResponseDto.of(page);
//        }
//
//        Page<BoardListResponse> page = categoryRepository
//                .getBoardByNewest(categoryId, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
//
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getLikeOrderList(Long userId, Long categoryId, Pageable pageable){
//
//        if(categoryId==9){
//            categoryId = getUserBootCampCategoryId(userId);
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
//
//            Page<BoardListResponse> page = categoryRepository
//                    .getBootBoardByLike(categoryId, user.getBootcamp().getId(), pageable)
//                    .map(BoardListResponse::of);
//
//            return GlobalPageResponseDto.of(page);
//        }
//
//        Page<BoardListResponse> page = categoryRepository
//                .getBoardByLike(categoryId, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
//
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getViewOrderList(Long userId, Long categoryId, Pageable pageable){
//
//        if(categoryId==9){
//            categoryId = getUserBootCampCategoryId(userId);
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
//
//            Page<BoardListResponse> page = categoryRepository
//                    .getBootBoardByView(categoryId, user.getBootcamp().getId(), pageable)
//                    .map(BoardListResponse::of);
//
//            return GlobalPageResponseDto.of(page);
//        }
//
//        Page<BoardListResponse> page = categoryRepository
//                .getBoardByView(categoryId, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
//
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getMainSearchTitleContentList(String keyword, Pageable pageable){
//
//        Page<BoardListResponse> page = categoryRepository
//                .getMainSearchByTitleContent(keyword, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
//
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getMainSearchNicknameList(String nickname, Pageable pageable){
//
//        Page<BoardListResponse> page = categoryRepository
//                .getMainSearchByNickname(nickname, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
//
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getSearchByTitleContent(Long userId, Long categoryId, String keyword, Pageable pageable){
//
//        if(categoryId==9){
//            categoryId = getUserBootCampCategoryId(userId);
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
//
//            Page<BoardListResponse> page = categoryRepository
//                    .getBootSearchByTitleContent(categoryId, user.getBootcamp().getId(), keyword, pageable)
//                    .map(BoardListResponse::of);
//
//            return GlobalPageResponseDto.of(page);
//        }
//
//        Page<BoardListResponse> page = categoryRepository
//                .getSearchByTitleContent(categoryId, keyword, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
//
//    @Transactional(readOnly = true)
//    public GlobalPageResponseDto<BoardListResponse> getSearchByNickname(Long userId, Long categoryId, String nickname, Pageable pageable){
//
//        if(categoryId==9){
//            categoryId = getUserBootCampCategoryId(userId);
//
//            User user = userRepository.findById(userId)
//                    .orElseThrow(() -> new BusinessException(ErrorMessage.USER_NOT_FOUND));
//
//            Page<BoardListResponse> page = categoryRepository
//                    .getBootSearchByNickname(categoryId, user.getBootcamp().getId(), nickname, pageable)
//                    .map(BoardListResponse::of);
//
//            return GlobalPageResponseDto.of(page);
//        }
//
//        Page<BoardListResponse> page = categoryRepository
//                .getSearchByNickname(categoryId, nickname, pageable)
//                .map(BoardListResponse::of);
//
//        return GlobalPageResponseDto.of(page);
//    }
}
