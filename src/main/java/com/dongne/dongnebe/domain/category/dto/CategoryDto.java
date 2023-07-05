package com.dongne.dongnebe.domain.category.dto;

import com.dongne.dongnebe.domain.category.enums.CategoryType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDto {
    private Long categoryId;
    private CategoryType categoryType;
    private String name;
}
