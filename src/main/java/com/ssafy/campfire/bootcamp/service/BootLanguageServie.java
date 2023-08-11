package com.ssafy.campfire.bootcamp.service;

import com.ssafy.campfire.bootcamp.domain.*;
import com.ssafy.campfire.bootcamp.dto.request.BootcampRequestDto;
import com.ssafy.campfire.bootcamp.repository.BootLanguageRepository;
import com.ssafy.campfire.bootcamp.repository.LanguageRepository;
import com.ssafy.campfire.utils.crawling.dto.Data;
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
    private  final LanguageRepository languageRepository;

    public List<Language> save(List<BootLanguage> bootLanguageList){
        List<Language> languageList = new ArrayList<>();
        if(bootLanguageList == null) return languageList;
        for (BootLanguage bootLanguage: bootLanguageList) {
            languageList.add(bootLanguageRepository.save(bootLanguage).getLanguage());
        }
        return languageList;
    }

    public List<Language> saveByCrawling(Data crawlingData, Bootcamp bootcamp) {
        List<String> keywords = crawlingData.getKeywords();

        List<BootLanguage> bootLanguageList = new ArrayList<>();
        for (String keyword : keywords) {
            Language language  = languageRepository.findByName(keyword);
            if(language == null){
                language  = languageRepository.save(new Language(keyword));
            }
            bootLanguageList.add(new BootLanguage(bootcamp, language));
        }
        return save(bootLanguageList);
    }


    public List<Language> updateByCrawling(Bootcamp originBootcamp, List<String> keywords) {
        List<BootLanguage> bootLanguageList = new ArrayList<>();
        for (String keyword : keywords) {
            Language language  = languageRepository.findByName(keyword);
            if(language == null){
                language  = languageRepository.save(new Language(keyword));
            }

            if(!bootLanguageRepository.existsBootLanguageByBootcampAndLanguage(originBootcamp.getId(), language.getId())){
                bootLanguageList.add(new BootLanguage(originBootcamp, language));
            }
            bootLanguageList.add(new BootLanguage(originBootcamp, language));

        }
        return save(bootLanguageList);
    }

    public Optional<List<Language>> getLanguageListByBootcampId(Long bootcampId) {
        Optional<List<Language>> languageList = bootLanguageRepository.getBootLanguagesByBootcampId(bootcampId);
        return languageList;
    }
    public void deleteBootLanguage(Long bootcampId) {
        bootLanguageRepository.deleteByBootcampId(bootcampId);
    }
    public List<Language> getLanguageList(){
        List<Language> languageList = Optional.of(languageRepository.findAll())
                .orElseThrow(() -> new BusinessException(ErrorMessage.REGION_NOT_FOUND));

        return languageList;
    }
}
