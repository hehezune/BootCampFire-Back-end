package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Track;

import java.util.List;
import java.util.Optional;

public interface CustomBootTrackRepository {
    Optional<List<Track>> getBootTracksByBootcampId(Long bootcampId);
    void deleteByBootcampId(Long bootcampId);

}
