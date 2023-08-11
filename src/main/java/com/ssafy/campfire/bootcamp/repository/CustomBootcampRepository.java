package com.ssafy.campfire.bootcamp.repository;


import com.ssafy.campfire.bootcamp.domain.Bootcamp;

import java.util.List;

public interface CustomBootcampRepository {


    // 부트캠프 리스트를 평점 순으로 보기
    List<Bootcamp> findAllByOrderByScoreDesc();
    Boolean existsBootcampByName(String name);
}
