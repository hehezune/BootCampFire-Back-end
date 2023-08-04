package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.BootLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BootLanguageRepository extends JpaRepository<BootLanguage, Long> , CustomBootLanguageRepository {
}
