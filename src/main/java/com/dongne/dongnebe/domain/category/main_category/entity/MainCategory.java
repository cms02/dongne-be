package com.dongne.dongnebe.domain.category.main_category.entity;

import com.dongne.dongnebe.domain.category.main_category.enums.MainCategoryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "main_category")
@Getter
@NoArgsConstructor
public class MainCategory {

    @Id
    @Column(name = "main_category_id")
    @GeneratedValue
    private Long mainCategoryId;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private MainCategoryType mainCategoryType;

    @Builder
    public MainCategory(Long mainCategoryId, MainCategoryType mainCategoryType) {
        this.mainCategoryId = mainCategoryId;
        this.mainCategoryType = mainCategoryType;
    }
}
