package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.BootTrack;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Track;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootTrackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BootTrackService {
    private final BootTrackRepository bootTrackRepository;

    public  List<Track> save(Bootcamp bootcamp, BootcampRegisterRequestDto bootcampRegisterRequestDto){
        List<BootTrack> bootTrackList = bootcampRegisterRequestDto.toBootTrackList(bootcamp);

        List<Track> trackList = new ArrayList<>();
        for (BootTrack bootTrack: bootTrackList) {
            trackList.add(bootTrackRepository.save(bootTrack).getTrack());

        }
        return trackList;
    }

    public Optional<List<Track>> getTrackListByBootcamp(Long bootcampId) {
        Optional<List<Track>> trackList = bootTrackRepository.getBootTracksByBootcampId(bootcampId);
        return trackList;
    }


}
