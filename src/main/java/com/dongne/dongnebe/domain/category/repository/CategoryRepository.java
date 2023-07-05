package com.dongne.dongnebe.domain.category.repository;

import com.dongne.dongnebe.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByOrderByCategoryIdAsc();
}
