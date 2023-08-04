package com.ssafy.campfire.bootcamp.repository;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BootcampRepository extends JpaRepository<Bootcamp, Long> ,CustomBootcampRepository{
    Optional<Bootcamp> findByName(String bootcampName);

    //이름순으로 정렬 Default
    List<Bootcamp> findAllByOrderByName();

    //후기순으로 정렬
    List<Bootcamp> findAllByOrderByReviewCntDesc();

    //algo_cnt 가 높은 순으로 10개 뽑기
    List<Bootcamp> findTop10ByOrderByAlgoCntDesc();


    List<Bootcamp> findByOrderByAlgoCntDesc();

    Long countAllByAlgoCntGreaterThanEqual(Integer algoCnt);

}
