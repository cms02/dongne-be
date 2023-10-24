package com.dongne.dongnebe.domain.city.repository;

import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.city.dto.CityNameCountDto;
import com.dongne.dongnebe.domain.city.entity.QCity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.dongne.dongnebe.domain.board.entity.QBoard.board;
import static com.dongne.dongnebe.domain.city.entity.QCity.city;

@Repository
@RequiredArgsConstructor
public class CityQueryRepository {
    private final JPAQueryFactory queryFactory;


    public List<CityNameCountDto> findBoardsTopNCity(Long cityCount) {
        StringPath aliasBoardCount = Expressions.stringPath("boardCount");
        QCity c = city;
        QBoard b = board;

        return queryFactory
                .select(Projections.fields(CityNameCountDto.class,
                        c.name,
                        b.count().as("boardCount")))
                .from(c)
                .where(b.isDeleted.eq(Boolean.FALSE))
                .join(c.boardList, b)
                .groupBy(c.cityCode)
                .orderBy(aliasBoardCount.desc(), c.name.asc())
                .limit(cityCount)
                .fetch();
    }
}
