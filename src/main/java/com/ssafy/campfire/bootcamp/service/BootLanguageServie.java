package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootLanguageRepository;
import com.ssafy.campfire.bootcamp.repository.BootLanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BootLanguageServie {
    private  final BootLanguageRepository bootLanguageRepository;

    public List<Language> save(Bootcamp bootcamp, BootcampRegisterRequestDto bootcampRegisterRequestDto){
        List<BootLanguage> bootLanguageList = bootcampRegisterRequestDto.toBootLanguageList(bootcamp);

        List<Language> languageList = new ArrayList<>();
        for (BootLanguage bootLanguage: bootLanguageList) {
            languageList.add(bootLanguageRepository.save(bootLanguage).getLanguage());

        }
        return languageList;
    }
}
