package com.dongne.dongnebe.domain.category.sub_category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SubCategoryDto {
    private Long subCategoryId;
    private String name;
}
