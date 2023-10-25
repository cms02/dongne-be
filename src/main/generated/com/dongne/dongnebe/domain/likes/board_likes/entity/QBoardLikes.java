package com.dongne.dongnebe.domain.likes.board_likes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardLikes is a Querydsl query type for BoardLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardLikes extends EntityPathBase<BoardLikes> {

    private static final long serialVersionUID = 1918490916L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardLikes boardLikes = new QBoardLikes("boardLikes");

    public final com.dongne.dongnebe.global.entity.QBaseEntity _super = new com.dongne.dongnebe.global.entity.QBaseEntity(this);

    public final com.dongne.dongnebe.domain.board.entity.QBoard board;

    public final NumberPath<Long> boardLikesId = createNumber("boardLikesId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final com.dongne.dongnebe.domain.user.entity.QUser user;

    public QBoardLikes(String variable) {
        this(BoardLikes.class, forVariable(variable), INITS);
    }

    public QBoardLikes(Path<? extends BoardLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardLikes(PathMetadata metadata, PathInits inits) {
        this(BoardLikes.class, metadata, inits);
    }

    public QBoardLikes(Class<? extends BoardLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.dongne.dongnebe.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.dongne.dongnebe.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

