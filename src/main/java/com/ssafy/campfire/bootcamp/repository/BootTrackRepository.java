package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.BootTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BootTrackRepository extends JpaRepository<BootTrack, Long> {
    List<BootTrack> findBootTracksByBootcamp_Id(Long bootcampId);
}
