package com.dongne.dongnebe.domain.category.sub_category.service;


import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.dto.response.SubCategoryResponseDto;
import com.dongne.dongnebe.domain.category.sub_category.repository.SubCategoryQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Transactional(readOnly = true)
    public SubCategoryResponseDto findAllSubCategories(@RequestBody FindDefaultBoardsRequestDto findDefaultBoardsRequestDto) {
        List<SubCategoryDto> subCategoryDtos = subCategoryQueryRepository.findAllSubCategories(findDefaultBoardsRequestDto);
        return SubCategoryResponseDto.builder()
                .subCategoryDtos(subCategoryDtos)
                .responseMessage("Find SubCategories")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
