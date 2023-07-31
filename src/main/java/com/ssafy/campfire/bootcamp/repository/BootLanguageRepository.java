package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.BootLanguage;
import com.ssafy.campfire.bootcamp.domain.BootTrack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BootLanguageRepository extends JpaRepository<BootLanguage, Long> {


}
