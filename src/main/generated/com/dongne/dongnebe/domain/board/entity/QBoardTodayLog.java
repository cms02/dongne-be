package com.dongne.dongnebe.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoardTodayLog is a Querydsl query type for BoardTodayLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardTodayLog extends EntityPathBase<BoardTodayLog> {

    private static final long serialVersionUID = 1203468294L;

    public static final QBoardTodayLog boardTodayLog = new QBoardTodayLog("boardTodayLog");

    public final com.dongne.dongnebe.global.entity.QBaseEntity _super = new com.dongne.dongnebe.global.entity.QBaseEntity(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> boardTodayLogId = createNumber("boardTodayLogId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final StringPath userId = createString("userId");

    public QBoardTodayLog(String variable) {
        super(BoardTodayLog.class, forVariable(variable));
    }

    public QBoardTodayLog(Path<? extends BoardTodayLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoardTodayLog(PathMetadata metadata) {
        super(BoardTodayLog.class, metadata);
    }

}

