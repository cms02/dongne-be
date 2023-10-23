package com.dongne.dongnebe.domain.category.sub_category.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.main_category.enums.MainCategoryType;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sub_category")
@Getter
@NoArgsConstructor
public class SubCategory {

    @Id
    @Column(name = "sub_category_id")
    @GeneratedValue
    private Long subCategoryId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;

    @OneToMany(mappedBy = "subCategory")
    private List<Board> boardList = new ArrayList<>();

    @Builder
    public SubCategory(Long subCategoryId, String name, MainCategory mainCategory) {
        this.subCategoryId = subCategoryId;
        this.name = name;
        this.mainCategory = mainCategory;
    }
}
