package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Region;

import java.util.List;
import java.util.Optional;

public interface CustomBootRegionRepository {
    Optional<List<Region>> getBootRegionsByBootcampId(Long bootcampId);
    void deleteByBootcampId(Long bootcampId);
}
