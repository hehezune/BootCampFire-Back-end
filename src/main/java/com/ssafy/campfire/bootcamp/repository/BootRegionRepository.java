package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.BootRegion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootRegionRepository extends JpaRepository<BootRegion, Long> ,CustomBootRegionRepository{

}
