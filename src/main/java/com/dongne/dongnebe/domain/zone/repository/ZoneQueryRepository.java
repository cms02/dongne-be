package com.dongne.dongnebe.domain.zone.repository;

import com.dongne.dongnebe.domain.zone.entity.QZone;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ZoneQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Zone> findAllByCityCodeOrderByZoneCodeAsc(String cityCode) {
        QZone z = QZone.zone;
        return queryFactory
                .selectFrom(z)
                .where(z.city.cityCode.eq(cityCode))
                .orderBy(z.zoneCode.asc())
                .fetch();
    }
}
