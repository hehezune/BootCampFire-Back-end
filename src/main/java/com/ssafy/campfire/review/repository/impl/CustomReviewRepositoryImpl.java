package com.ssafy.campfire.review.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.review.domain.Review;
import com.ssafy.campfire.review.repository.CustomReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.campfire.bootcamp.domain.QBootcamp.bootcamp;
import static com.ssafy.campfire.review.domain.QReview.review;
import static com.ssafy.campfire.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class CustomReviewRepositoryImpl implements CustomReviewRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Review> getReviewList(Long bootcampId) {
        List<Review> reviews = queryFactory.select(review)
                .from(review)
                .leftJoin(review.bootcamp, bootcamp)
                .fetchJoin()
                .leftJoin(review.user, user)
                .fetchJoin()
                .where(
                        review.bootcamp.id.eq(bootcampId)
                )
                .orderBy(review.createdDate.asc())
                .fetch();
        return reviews;
    }
}
