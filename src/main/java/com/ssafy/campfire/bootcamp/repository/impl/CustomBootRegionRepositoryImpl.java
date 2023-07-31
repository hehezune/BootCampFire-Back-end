package com.ssafy.campfire.bootcamp.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.repository.CustomBootRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.campfire.bootcamp.domain.QBootRegion.bootRegion;
import static com.ssafy.campfire.bootcamp.domain.QRegion.region;

@Repository
@RequiredArgsConstructor
public class CustomBootRegionRepositoryImpl implements CustomBootRegionRepository {
    private final JPAQueryFactory queryFactory;
    @Override
    public Optional<List<Region>> getBootRegionsByBootcampId(Long bootcampId) {
        List<Region> regionList = (List<Region>) queryFactory.select(region)
                .from(bootRegion)
                .where(bootRegion.bootcamp.id.eq(bootcampId))
                .fetch();
        return Optional.ofNullable(regionList);
    }
}
