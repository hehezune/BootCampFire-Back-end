package com.ssafy.campfire.category.repository;

import com.ssafy.campfire.bootcamp.domain.Bootcamp;
import com.ssafy.campfire.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, CustomCategoryRepository {
    Optional<Category> findCategoryByBootcamp(Bootcamp bootcamp);
}
