package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.BootRegion;
import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Region;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootRegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional
public class BootRegionService {

    private  final BootRegionRepository bootRegionRepository;

    public List<Region> save(Bootcamp bootcamp, BootcampRegisterRequestDto bootcampRegisterRequestDto) {
        List<BootRegion> bootRegionList = bootcampRegisterRequestDto.toBootRegionList(bootcamp);

        List<Region> regionList = new ArrayList<>();
        for (BootRegion bootRegion : bootRegionList) {
            regionList.add(bootRegionRepository.save(bootRegion).getRegion());

        }
        return regionList;
    }
}
