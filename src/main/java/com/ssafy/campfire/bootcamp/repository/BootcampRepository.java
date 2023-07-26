package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {

}
