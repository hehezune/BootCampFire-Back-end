package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.BootTrack;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Track;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootTrackRepository;
import com.ssafy.campfire.bootcamp.repository.TrackRepository;
import com.ssafy.campfire.utils.crawling.dto.Data;
import com.ssafy.campfire.utils.crawling.enums.CategoryType;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
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
    private final TrackRepository trackRepository;

    public  List<Track> save(List<BootTrack> bootTrackList){
        List<Track> trackList = new ArrayList<>();
        if(bootTrackList == null) return trackList;
        for (BootTrack bootTrack: bootTrackList) {
            trackList.add(bootTrackRepository.save(bootTrack).getTrack());
        }
        return trackList;
    }

    public List<Track> saveByCrawling(Data crawlingData, Bootcamp bootcamp) {
        List<String> categories = crawlingData.getCategories();

        List<BootTrack> bootTrackList = new ArrayList<>();
        for(String category : categories) {
            Track t = trackRepository.findByName(CategoryType.valueOf(category).getMessage());
            bootTrackList.add(new BootTrack(bootcamp, t));
        }
        return save(bootTrackList);
    }

    public List<Track> updateByCrawling(Bootcamp originBootcamp,  List<String> categories) {
        List<BootTrack> bootTrackList = new ArrayList<>();
        for(String category : categories) {
            Track t = trackRepository.findByName(CategoryType.valueOf(category).getMessage());
            if(!bootTrackRepository.existsBootTrackByBootcampAndTrack(originBootcamp.getId(), t.getId())){
                bootTrackList.add(new BootTrack(originBootcamp, t));
            }
        }
        return save(bootTrackList);


    }

    public Optional<List<Track>> getTrackListByBootcampId(Long bootcampId) {
        Optional<List<Track>> trackList = bootTrackRepository.getBootTracksByBootcampId(bootcampId);
        return trackList;
    }

    public void deleteBootTrack(Long bootcampId) {
        bootTrackRepository.deleteByBootcampId(bootcampId);
    }

    public List<Track> getTrackList(){
        List<Track> trackList = Optional.of(trackRepository.findAll())
                .orElseThrow(() -> new BusinessException(ErrorMessage.REGION_NOT_FOUND));
        return trackList;
    }


}
