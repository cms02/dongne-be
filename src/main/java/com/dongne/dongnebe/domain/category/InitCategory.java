package com.dongne.dongnebe.domain.category;

import com.dongne.dongnebe.domain.category.entity.Category;
import com.dongne.dongnebe.domain.category.enums.CategoryType;
import com.dongne.dongnebe.domain.category.repository.CategoryRepository;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.city.repository.CityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Profile({"local","dev"})
@Component
@RequiredArgsConstructor
public class InitCategory {

    private final InitCategoryService initCategoryService;

    @PostConstruct
    public void init() {
        initCategoryService.initCategory();
    }

    @Component
    @RequiredArgsConstructor
    static class InitCategoryService {

        private final CategoryRepository categoryRepository;

        @Transactional
        public void initCategory() {
            categoryRepository.save(Category.builder()
                    .name("사는얘기")
                    .categoryType(CategoryType.STORY)
                    .build());
        }
    }
}
