package com.ssafy.campfire.bootcamp.dto.request;

import com.ssafy.campfire.bootcamp.domain.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record BootcampRequestDto(
        String name,
        String siteUrl,
        String process,
        String schedule,
        String description,
        Double cost,
        Boolean card,
        Boolean support,
        Boolean hasCodingtest,
        String onOff,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String imgUrl,
        List<Track> tracks,
        List<Language> languages,
        List<Region> regions
) {
    public Bootcamp toBootcamp(){
        return new Bootcamp( name, siteUrl,  process, schedule, description,  cost,card,support,hasCodingtest,onOff,startDate,endDate, imgUrl);
    }
    public List<BootTrack> toBootTrackList(Bootcamp bootcamp){
        List<BootTrack> bootTrackList = new ArrayList<>();
        for (Track t:tracks) {
            bootTrackList.add(new BootTrack(bootcamp, t));
        }
        return bootTrackList;
    }

    public List<BootLanguage> toBootLanguageList(Bootcamp bootcamp){
        List<BootLanguage> bootLanguageList = new ArrayList<>();
        for (Language l:languages) {
            bootLanguageList.add(new BootLanguage(bootcamp, l));
        }
        return bootLanguageList;
    }

    public List<BootRegion> toBootRegionList(Bootcamp bootcamp){
        List<BootRegion> bootRegionList = new ArrayList<>();
        for (Region r:regions) {
            bootRegionList.add(new BootRegion(bootcamp, r));
        }
        return bootRegionList;
    }
}
