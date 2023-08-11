package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.bootcamp.domain.Language;

import java.util.List;
import java.util.Optional;

public interface CustomBootLanguageRepository {
    Optional<List<Language>> getBootLanguagesByBootcampId(Long bootcampId);
    void deleteByBootcampId(Long bootcampId);

    Boolean existsBootLanguageByBootcampAndLanguage(Long bootcampId, Long languageId);
}
