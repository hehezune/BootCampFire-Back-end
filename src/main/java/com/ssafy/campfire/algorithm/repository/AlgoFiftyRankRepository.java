package com.ssafy.campfire.algorithm.repository;

import com.ssafy.campfire.algorithm.domain.AlgoFiftyRank;
import com.ssafy.campfire.algorithm.domain.Algorithm;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlgoFiftyRankRepository extends JpaRepository<AlgoFiftyRank, Long> {
    AlgoFiftyRank findFirstByOrderByCreatedDateDesc();

    List<AlgoFiftyRank> findTop10ByAlgorithmOrderByRank(Algorithm algorithm);

    AlgoFiftyRank findByAlgorithmAndBootcamp(Algorithm algorithm, Bootcamp bootcamp);
}
