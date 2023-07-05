package com.dongne.dongnebe.domain.category.entity;

import com.dongne.dongnebe.domain.category.enums.CategoryType;
import com.dongne.dongnebe.domain.user.enums.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "category")
@Getter
@NoArgsConstructor
public class Category {

    @Id
    @Column(name = "category_id")
    @GeneratedValue
    private Long categoryId;
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;

    @Builder
    public Category(String name, CategoryType categoryType) {
        this.name = name;
        this.categoryType = categoryType;
    }
}
