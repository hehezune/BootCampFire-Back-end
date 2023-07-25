package com.ssafy.campfire.board.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.campfire.board.repository.CustomBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomBoardRepositoryImpl implements CustomBoardRepository {

    private final JPAQueryFactory queryFactory;

}
