package com.dongne.dongnebe.domain.category.sub_category.repository;

import com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.zone.entity.QZone;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubCategoryQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<SubCategory> findAllSubCategoriesByMainCategoryIdOrderBySubCategoryId(Long mainCategoryId) {
        QSubCategory s = QSubCategory.subCategory;
        return queryFactory
                .selectFrom(s)
                .where(s.mainCategory.mainCategoryId.eq(mainCategoryId))
                .orderBy(s.subCategoryId.asc())
                .fetch();
    }
}
