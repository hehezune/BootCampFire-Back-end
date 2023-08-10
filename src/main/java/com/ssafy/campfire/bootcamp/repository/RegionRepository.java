package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findByName(String name);
}
