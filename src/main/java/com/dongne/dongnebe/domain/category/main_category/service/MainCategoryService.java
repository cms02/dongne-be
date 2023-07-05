package com.dongne.dongnebe.domain.category.main_category.service;


import com.dongne.dongnebe.domain.category.main_category.dto.MainCategoryDto;
import com.dongne.dongnebe.domain.category.main_category.dto.MainCategoryResponseDto;
import com.dongne.dongnebe.domain.category.main_category.repository.MainCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    public MainCategoryResponseDto findAllByOrderByMainCategoryIdAsc() {
        List<MainCategoryDto> mainCategoryDtos = mainCategoryRepository.findAllByOrderByMainCategoryIdAsc().stream()
                .map(c -> MainCategoryDto.builder()
                        .mainCategoryId(c.getMainCategoryId())
                        .mainCategoryType(c.getMainCategoryType())
                        .build())
                .collect(Collectors.toList());
        return MainCategoryResponseDto.builder()
                .mainCategoryDtos(mainCategoryDtos)
                .responseMessage("Find MainCategories")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
