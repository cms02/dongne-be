package com.dongne.dongnebe.domain.category.main_category.dto;

import com.dongne.dongnebe.domain.category.main_category.enums.MainCategoryType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MainCategoryDto {
    private Long mainCategoryId;
    private MainCategoryType mainCategoryType;
}
