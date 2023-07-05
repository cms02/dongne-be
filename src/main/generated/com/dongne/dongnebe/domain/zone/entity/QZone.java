package com.dongne.dongnebe.domain.zone.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QZone is a Querydsl query type for Zone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QZone extends EntityPathBase<Zone> {

    private static final long serialVersionUID = -1333692587L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QZone zone = new QZone("zone");

    public final com.dongne.dongnebe.domain.city.entity.QCity city;

    public final StringPath name = createString("name");

    public final StringPath zoneCode = createString("zoneCode");

    public QZone(String variable) {
        this(Zone.class, forVariable(variable), INITS);
    }

    public QZone(Path<? extends Zone> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QZone(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QZone(PathMetadata metadata, PathInits inits) {
        this(Zone.class, metadata, inits);
    }

    public QZone(Class<? extends Zone> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new com.dongne.dongnebe.domain.city.entity.QCity(forProperty("city")) : null;
    }

}

