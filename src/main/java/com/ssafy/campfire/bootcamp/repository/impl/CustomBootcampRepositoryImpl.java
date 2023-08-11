package com.ssafy.campfire.bootcamp.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.repository.CustomBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.campfire.bootcamp.domain.QBootcamp.bootcamp;

@Repository
@RequiredArgsConstructor
public class CustomBootcampRepositoryImpl implements CustomBootcampRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Bootcamp> findAllByOrderByScoreDesc() {
        List<Bootcamp> bootcampList;
        bootcampList = queryFactory.select(bootcamp)
                .from(bootcamp)
                .orderBy(bootcamp.totalScore.divide(bootcamp.reviewCnt).desc())
                .fetch();
        return bootcampList;
    }

    @Override
    public Boolean existsBootcampByName(String name) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(bootcamp)
                .where(
                        bootcamp.name.eq(name)
                )
                .fetchOne();
        return fetchOne != null;
    }
}
