package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRegisterRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootLanguageRepository;
import com.ssafy.campfire.bootcamp.repository.LanguageRepository;
import com.ssafy.campfire.utils.dto.response.BaseResponseDto;
import com.ssafy.campfire.utils.error.enums.ErrorMessage;
import com.ssafy.campfire.utils.error.exception.custom.BusinessException;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class BootLanguageServie {
    private  final BootLanguageRepository bootLanguageRepository;
    private  final LanguageRepository LanguageRepository;

    public List<Language> save(Bootcamp bootcamp, BootcampRegisterRequestDto bootcampRegisterRequestDto){
        List<BootLanguage> bootLanguageList = bootcampRegisterRequestDto.toBootLanguageList(bootcamp);

        List<Language> languageList = new ArrayList<>();
        for (BootLanguage bootLanguage: bootLanguageList) {
            languageList.add(bootLanguageRepository.save(bootLanguage).getLanguage());

        }
        return languageList;
    }


    public Optional<List<Language>> getLanguageListByBootcamp(Long bootcampId) {
        Optional<List<Language>> languageList = bootLanguageRepository.getBootLanguagesByBootcampId(bootcampId);
        return languageList;
    }

    public List<Language> getLanguageList(){
        List<Language> languageList = Optional.of(LanguageRepository.findAll())
                .orElseThrow(() -> new BusinessException(ErrorMessage.REGION_NOT_FOUND));

        return languageList;
    }
}
