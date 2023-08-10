package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootRegionRepository;
import com.ssafy.campfire.bootcamp.repository.RegionRepository;
import com.ssafy.campfire.utils.crawling.dto.Data;
import com.ssafy.campfire.utils.crawling.enums.CategoryType;
import com.ssafy.campfire.utils.crawling.enums.CityType;
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
public class BootRegionService {

    private  final BootRegionRepository bootRegionRepository;
    private  final RegionRepository regionRepository;

    public List<Region> save(List<BootRegion> bootRegionList) {
        List<Region> regionList = new ArrayList<>();
        if(bootRegionList == null) return regionList;
        for (BootRegion bootRegion : bootRegionList) {
            regionList.add(bootRegionRepository.save(bootRegion).getRegion());

        }
        return regionList;
    }

    public List<Region> saveByCrawling(Data crawlingData, Bootcamp bootcamp) {
        List<String> cities = crawlingData.getCity();

        List<BootRegion> bootRegionList = new ArrayList<>();
        if(cities == null) return save(bootRegionList);
        for(String city : cities) {
            if (city.equals("")) continue;
            Region r = regionRepository.findByName(CityType.valueOf(city).getMessage());
            bootRegionList.add(new BootRegion(bootcamp, r));
        }
        return save(bootRegionList);
//        return  null;
    }

    public Optional<List<Region>> getRegionListByBootcampId(Long bootcampId) {
        Optional<List<Region>> regionList = bootRegionRepository.getBootRegionsByBootcampId(bootcampId);
        return regionList;
    }
    public void deleteBootRegion(Long bootcampId) {
        bootRegionRepository.deleteByBootcampId(bootcampId);
    }

    public List<Region> getRegionList(){
        List<Region> regionList = Optional.of(regionRepository.findAll())
                .orElseThrow(() -> new BusinessException(ErrorMessage.REGION_NOT_FOUND));

        return regionList;
    }

}
