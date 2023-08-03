package com.ssafy.campfire.algorithm.repository;

import com.ssafy.campfire.algorithm.domain.AlgoFiftyRank;
import com.ssafy.campfire.algorithm.domain.Algorithm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlgoFiftyRankRepository extends JpaRepository<AlgoFiftyRank, Long> {
}
