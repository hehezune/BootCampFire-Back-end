package com.ssafy.campfire.algorithm.repository;

import com.ssafy.campfire.algorithm.domain.AlgoFiftyRank;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlgoFiftyRankRepository extends JpaRepository<AlgoFiftyRank, Long> {
    AlgoFiftyRank findFirstByOrderByCreatedDateDesc();
}
