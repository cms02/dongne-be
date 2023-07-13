package com.dongne.dongnebe.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 852274851L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.dongne.dongnebe.global.entity.QBaseEntity _super = new com.dongne.dongnebe.global.entity.QBaseEntity(this);

    public final ListPath<com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment, com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment> boardComments = this.<com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment, com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment>createList("boardComments", com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment.class, com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final EnumPath<com.dongne.dongnebe.domain.board.enums.BoardType> boardType = createEnum("boardType", com.dongne.dongnebe.domain.board.enums.BoardType.class);

    public final com.dongne.dongnebe.domain.category.channel.entity.QChannel channel;

    public final com.dongne.dongnebe.domain.city.entity.QCity city;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final DateTimePath<java.time.LocalDateTime> deadlineAt = createDateTime("deadlineAt", java.time.LocalDateTime.class);

    public final StringPath fileImg = createString("fileImg");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final com.dongne.dongnebe.domain.category.main_category.entity.QMainCategory mainCategory;

    public final com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory subCategory;

    public final StringPath title = createString("title");

    public final com.dongne.dongnebe.domain.user.entity.QUser user;

    public final NumberPath<Long> viewCnt = createNumber("viewCnt", Long.class);

    public final com.dongne.dongnebe.domain.zone.entity.QZone zone;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.channel = inits.isInitialized("channel") ? new com.dongne.dongnebe.domain.category.channel.entity.QChannel(forProperty("channel"), inits.get("channel")) : null;
        this.city = inits.isInitialized("city") ? new com.dongne.dongnebe.domain.city.entity.QCity(forProperty("city")) : null;
        this.mainCategory = inits.isInitialized("mainCategory") ? new com.dongne.dongnebe.domain.category.main_category.entity.QMainCategory(forProperty("mainCategory")) : null;
        this.subCategory = inits.isInitialized("subCategory") ? new com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory(forProperty("subCategory"), inits.get("subCategory")) : null;
        this.user = inits.isInitialized("user") ? new com.dongne.dongnebe.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
        this.zone = inits.isInitialized("zone") ? new com.dongne.dongnebe.domain.zone.entity.QZone(forProperty("zone"), inits.get("zone")) : null;
    }

}

