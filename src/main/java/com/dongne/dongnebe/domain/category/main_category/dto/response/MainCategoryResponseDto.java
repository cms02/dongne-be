package com.dongne.dongnebe.domain.category.main_category.dto.response;

import com.dongne.dongnebe.domain.category.main_category.dto.MainCategoryDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class MainCategoryResponseDto extends ResponseDto {
    private List<MainCategoryDto> mainCategoryDtos;
}
