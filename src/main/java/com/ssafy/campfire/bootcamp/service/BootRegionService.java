package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.BootRegion;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootRegionRepository;
import com.ssafy.campfire.bootcamp.repository.RegionRepository;
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

    public List<Region> save(Bootcamp bootcamp, BootcampRequestDto bootcampRegisterRequestDto) {
        List<BootRegion> bootRegionList = bootcampRegisterRequestDto.toBootRegionList(bootcamp);

        List<Region> regionList = new ArrayList<>();
        for (BootRegion bootRegion : bootRegionList) {
            regionList.add(bootRegionRepository.save(bootRegion).getRegion());

        }
        return regionList;
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
