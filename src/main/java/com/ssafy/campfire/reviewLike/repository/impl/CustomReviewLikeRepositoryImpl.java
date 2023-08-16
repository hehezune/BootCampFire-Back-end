package com.ssafy.campfire.reviewLike.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.reviewLike.repository.CustomReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.ssafy.campfire.reviewLike.domain.QReviewLike.reviewLike;


@Repository
@RequiredArgsConstructor
public class CustomReviewLikeRepositoryImpl implements CustomReviewLikeRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Boolean hasLikeByUserId(Long reviewId, Long userId) {
        return jpaQueryFactory.selectFrom(reviewLike)
                .where(reviewLike.user.id.eq(userId), reviewLike.review.id.eq(reviewId))
                .fetchOne() != null;
    }

    @Override
    public Long deleteByReview(Long reviewId) {
        return jpaQueryFactory.delete(reviewLike)
                .where(reviewLike.review.id.eq(reviewId))
                .execute();
    }
}
