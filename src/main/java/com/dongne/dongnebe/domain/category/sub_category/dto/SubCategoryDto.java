package com.dongne.dongnebe.domain.category.sub_category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryDto {
    private Long subCategoryId;
    private String name;
}
