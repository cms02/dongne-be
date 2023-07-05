package com.dongne.dongnebe.domain.category.main_category.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainCategoryType {
    STORY("일상다반사"),
    NEWS("사뭇진지"),
    FUN("연예재미"),
    MARRIAGE("결혼과 나"),
    RELATIONSHIP("사랑그리고"),
    WORK("직장과 삶"),
    LIFE("생활문화");

    private String value;
}
