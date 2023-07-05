package com.dongne.dongnebe.domain.category.main_category.repository;

import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.main_category.enums.MainCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Long> {
    List<MainCategory> findAllByOrderByMainCategoryIdAsc();

    MainCategory findByMainCategoryType(MainCategoryType mainCategoryType);
}
