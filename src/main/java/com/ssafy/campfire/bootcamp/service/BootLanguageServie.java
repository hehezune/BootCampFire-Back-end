package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootLanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Language> getLanguageListByBootcamp(Optional<Bootcamp> bootcamp) {
        List<BootLanguage> bootLanguageList = bootLanguageRepository.findByBootcamp(bootcamp);
        List<Language> languageList = new ArrayList<>();
        for (BootLanguage bootLanguage: bootLanguageList) {
            languageList.add(bootLanguage.getLanguage());
        }
        return languageList;
        
    }
}
