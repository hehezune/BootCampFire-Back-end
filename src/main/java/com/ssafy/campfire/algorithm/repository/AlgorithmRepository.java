package com.ssafy.campfire.algorithm.repository;

import com.ssafy.campfire.algorithm.domain.Algorithm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AlgorithmRepository extends JpaRepository<Algorithm, Long> {
    Optional<Algorithm> findAlgorithmByDate(LocalDate date);
}
