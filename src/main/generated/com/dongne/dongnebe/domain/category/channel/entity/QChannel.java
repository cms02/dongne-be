package com.dongne.dongnebe.domain.category.channel.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChannel is a Querydsl query type for Channel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChannel extends EntityPathBase<Channel> {

    private static final long serialVersionUID = -449869453L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChannel channel = new QChannel("channel");

    public final ListPath<com.dongne.dongnebe.domain.board.entity.Board, com.dongne.dongnebe.domain.board.entity.QBoard> boardList = this.<com.dongne.dongnebe.domain.board.entity.Board, com.dongne.dongnebe.domain.board.entity.QBoard>createList("boardList", com.dongne.dongnebe.domain.board.entity.Board.class, com.dongne.dongnebe.domain.board.entity.QBoard.class, PathInits.DIRECT2);

    public final NumberPath<Long> channelId = createNumber("channelId", Long.class);

    public final StringPath name = createString("name");

    public final com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory subCategory;

    public final com.dongne.dongnebe.domain.zone.entity.QZone zone;

    public QChannel(String variable) {
        this(Channel.class, forVariable(variable), INITS);
    }

    public QChannel(Path<? extends Channel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChannel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChannel(PathMetadata metadata, PathInits inits) {
        this(Channel.class, metadata, inits);
    }

    public QChannel(Class<? extends Channel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.subCategory = inits.isInitialized("subCategory") ? new com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
        this.zone = inits.isInitialized("zone") ? new com.dongne.dongnebe.domain.zone.entity.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

