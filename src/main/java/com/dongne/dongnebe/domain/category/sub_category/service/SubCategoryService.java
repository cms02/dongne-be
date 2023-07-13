package com.dongne.dongnebe.domain.category.sub_category.service;


import com.dongne.dongnebe.domain.category.main_category.dto.MainCategoryDto;
import com.dongne.dongnebe.domain.category.main_category.dto.MainCategoryResponseDto;
import com.dongne.dongnebe.domain.category.main_category.repository.MainCategoryRepository;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryResponseDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.category.sub_category.repository.SubCategoryQueryRepository;
import com.dongne.dongnebe.domain.category.sub_category.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final SubCategoryQueryRepository subCategoryQueryRepository;

    @Transactional(readOnly = true)
    public SubCategoryResponseDto findAllSubCategoriesByMainCategoryIdOrderBySubCategoryId(Long mainCategoryId) {
        List<SubCategoryDto> subCategoryDtos = subCategoryQueryRepository.findAllSubCategoriesByMainCategoryIdOrderBySubCategoryId(mainCategoryId).stream()
                .map(s -> SubCategoryDto.builder()
                        .subCategoryId(s.getSubCategoryId())
                        .name(s.getName())
                        .build())
                .collect(Collectors.toList());
        return SubCategoryResponseDto.builder()
                .subCategoryDtos(subCategoryDtos)
                .responseMessage("Find SubCategories")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
