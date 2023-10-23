package com.dongne.dongnebe.domain.category.sub_category.repository;

import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dongne.dongnebe.domain.board.entity.QBoard.board;
import static com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory.subCategory;

@Repository
@RequiredArgsConstructor
public class SubCategoryQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<SubCategory> findAllSubCategoriesByMainCategoryIdOrderBySubCategoryId(Long mainCategoryId) {
        QSubCategory s = subCategory;
        return queryFactory
                .selectFrom(s)
                .where(s.mainCategory.mainCategoryId.eq(mainCategoryId))
                .orderBy(s.subCategoryId.asc())
                .fetch();
    }

    public List<SubCategoryDto> findAllSubCategories(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto) {
        StringPath aliasBoardCount = Expressions.stringPath("boardCount");
        QSubCategory s = subCategory;
        QBoard b = board;
        return queryFactory
                .select(Projections.fields(SubCategoryDto.class,
                                s.subCategoryId,
                                s.name,
                                ExpressionUtils.as(JPAExpressions
                                                .select(b.count())
                                                .from(b)
                                                .where(b.subCategory.subCategoryId.eq(s.subCategoryId)
                                                        .and(b.city.cityCode.eq(findDefaultBoardsRequestDto.getCityCode()))
                                                        .and(b.zone.zoneCode.eq(findDefaultBoardsRequestDto.getZoneCode())))
                                        , "boardCount")
                        )
                )
                .from(s)
                .orderBy(aliasBoardCount.desc(),s.subCategoryId.asc())
                .fetch();
    }

}
