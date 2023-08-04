package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> {
    Optional<Bootcamp> findByName(String bootcampName);
    Optional<Bootcamp> findById(Long bootcampId);

    //이름순으로 정렬 Default
    List<Bootcamp> findAllByOrderByName();


    //평점순으로 정렬
    List<Bootcamp> findAllByOrderByTotalScore();

    //후기순으로 정렬


}
