package com.dongne.dongnebe.domain.category.dto;

import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class CategoryResponseDto extends ResponseDto {
    private List<CategoryDto> categoryDtos;
}
