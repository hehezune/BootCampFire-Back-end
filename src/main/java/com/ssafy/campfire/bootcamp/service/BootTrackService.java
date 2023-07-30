package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.BootTrack;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Track;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootTrackRepository;
import com.ssafy.campfire.bootcamp.repository.TrackRepository;
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

    public List<Track> getTrackListByBootcamp(Long bootcampId) {
        List<BootTrack> bootTrackList = bootTrackRepository.findByBootcamp(bootcamp);
    }


//    public List<Track> getTrackListByBootcamp(Optional<Bootcamp> bootcamp) {
//        System.out.println("부트트랙서비스 ---------------------");
////        List<BootTrack> bootTrackList = bootTrackRepository.findByBootcamp(bootcamp);
//        List<BootTrack> bootTrackList = bootTrackRepository.findBootTracksByBootcamp(bootcamp);
//        List<Track> trackList = new ArrayList<>();
//        System.out.println(bootTrackList.size());
//        for (BootTrack bootTrack: bootTrackList) {
//            System.out.println(bootTrack.toString());
//            System.out.println(bootTrack.getTrack().toString());
//            trackList.add(bootTrack.getTrack());
//        }
//        return trackList;
//    }
}
