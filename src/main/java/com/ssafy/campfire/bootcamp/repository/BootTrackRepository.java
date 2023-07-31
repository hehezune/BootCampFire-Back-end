package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.BootTrack;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BootTrackRepository extends JpaRepository<BootTrack, Long>, CustomBootTrackRepository {

}
