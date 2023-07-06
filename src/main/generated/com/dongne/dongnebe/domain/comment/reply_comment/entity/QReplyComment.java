package com.dongne.dongnebe.domain.comment.reply_comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReplyComment is a Querydsl query type for ReplyComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplyComment extends EntityPathBase<ReplyComment> {

    private static final long serialVersionUID = 987628301L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReplyComment replyComment = new QReplyComment("replyComment");

    public final com.dongne.dongnebe.global.entity.QBaseEntity _super = new com.dongne.dongnebe.global.entity.QBaseEntity(this);

    public final com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment boardComment;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final NumberPath<Long> replyCommentId = createNumber("replyCommentId", Long.class);

    public final com.dongne.dongnebe.domain.user.entity.QUser user;

    public QReplyComment(String variable) {
        this(ReplyComment.class, forVariable(variable), INITS);
    }

    public QReplyComment(Path<? extends ReplyComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplyComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplyComment(PathMetadata metadata, PathInits inits) {
        this(ReplyComment.class, metadata, inits);
    }

    public QReplyComment(Class<? extends ReplyComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardComment = inits.isInitialized("boardComment") ? new com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment(forProperty("boardComment"), inits.get("boardComment")) : null;
        this.user = inits.isInitialized("user") ? new com.dongne.dongnebe.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

