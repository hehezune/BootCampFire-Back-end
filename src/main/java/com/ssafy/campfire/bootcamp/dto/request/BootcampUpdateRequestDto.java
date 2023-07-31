//package com.ssafy.campfire.bootcamp.dto.request;
//
//import com.ssafy.campfire.bootcamp.domain.*;
//
//import javax.validation.constraints.NotBlank;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public record BootcampUpdateRequestDto(
//        @NotBlank(message = "이름은 필수입니다.")
//        String name,
//        String siteUrl,
//        String process,
//        String schedule,
//        String description,
//        Double cost,
//        Boolean card,
//        Boolean support,
//        Boolean hasCodingtest,
//        String on_off,
//        LocalDateTime startDate,
//        LocalDateTime endDate,
//        List<Track> tracks,
//        List<Language> languages,
//        List<Region> regions
//) {
//    public Bootcamp toBootcamp(){
//        return new Bootcamp( name, siteUrl,  process, schedule, description,  cost,card,support,hasCodingtest,on_off,startDate,endDate);
//    }
//    public List<BootTrack> toBootTrackList(Bootcamp bootcamp){
//        List<BootTrack> bootTrackList = new ArrayList<>();
//        for (Track t:tracks) {
//            bootTrackList.add(new BootTrack(bootcamp, t));
//        }
//        return bootTrackList;
//    }
//
//    public List<BootLanguage> toBootLanguageList(Bootcamp bootcamp){
//        List<BootLanguage> bootLanguageList = new ArrayList<>();
//        for (Language l:languages) {
//            bootLanguageList.add(new BootLanguage(bootcamp, l));
//        }
//        return bootLanguageList;
//    }
//
//    public List<BootRegion> toBootRegionList(Bootcamp bootcamp){
//        List<BootRegion> bootRegionList = new ArrayList<>();
//        for (Region r:regions) {
//            bootRegionList.add(new BootRegion(bootcamp, r));
//        }
//        return bootRegionList;
//    }
//}
