package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.BootRegion;
import com.ssafy.campfire.bootcamp.domain.BootTrack;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BootRegionRepository extends JpaRepository<BootRegion, Long> ,CustomBootRegionRepository{
}
