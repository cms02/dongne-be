package com.dongne.dongnebe.domain.category.service;


import com.dongne.dongnebe.domain.category.dto.CategoryDto;
import com.dongne.dongnebe.domain.category.dto.CategoryResponseDto;
import com.dongne.dongnebe.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryResponseDto findAllByOrderByCategoryIdAsc() {
        List<CategoryDto> categoryDtos = categoryRepository.findAllByOrderByCategoryIdAsc().stream()
                .map(c -> CategoryDto.builder()
                        .categoryId(c.getCategoryId())
                        .categoryType(c.getCategoryType())
                                .name(c.getName())
                        .build())
                .collect(Collectors.toList());
        return CategoryResponseDto.builder()
                .categoryDtos(categoryDtos)
                .responseMessage("Find Categories")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
