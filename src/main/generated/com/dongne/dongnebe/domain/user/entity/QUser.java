package com.dongne.dongnebe.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -605105901L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.dongne.dongnebe.global.entity.QBaseEntity _super = new com.dongne.dongnebe.global.entity.QBaseEntity(this);

    public final com.dongne.dongnebe.domain.city.entity.QCity city;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final NumberPath<Long> point = createNumber("point", Long.class);

    public final StringPath profileImg = createString("profileImg");

    public final EnumPath<com.dongne.dongnebe.domain.user.enums.Role> role = createEnum("role", com.dongne.dongnebe.domain.user.enums.Role.class);

    public final StringPath userId = createString("userId");

    public final StringPath username = createString("username");

    public final com.dongne.dongnebe.domain.zone.entity.QZone zone;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.city = inits.isInitialized("city") ? new com.dongne.dongnebe.domain.city.entity.QCity(forProperty("city")) : null;
        this.zone = inits.isInitialized("zone") ? new com.dongne.dongnebe.domain.zone.entity.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

