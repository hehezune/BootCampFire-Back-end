package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track findByName(String name);
}
