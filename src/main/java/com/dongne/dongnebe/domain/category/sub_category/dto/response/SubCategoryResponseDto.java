package com.dongne.dongnebe.domain.category.sub_category.dto.response;

import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class SubCategoryResponseDto extends ResponseDto {
    private List<SubCategoryDto> subCategoryDtos;
}
