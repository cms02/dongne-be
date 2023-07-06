package com.dongne.dongnebe.domain.likes.board_comment_likes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardCommentLikes is a Querydsl query type for BoardCommentLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardCommentLikes extends EntityPathBase<BoardCommentLikes> {

    private static final long serialVersionUID = -269899499L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardCommentLikes boardCommentLikes = new QBoardCommentLikes("boardCommentLikes");

    public final com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment boardComment;

    public final NumberPath<Long> boardCommentLikesId = createNumber("boardCommentLikesId", Long.class);

    public final com.dongne.dongnebe.domain.user.entity.QUser user;

    public QBoardCommentLikes(String variable) {
        this(BoardCommentLikes.class, forVariable(variable), INITS);
    }

    public QBoardCommentLikes(Path<? extends BoardCommentLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardCommentLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardCommentLikes(PathMetadata metadata, PathInits inits) {
        this(BoardCommentLikes.class, metadata, inits);
    }

    public QBoardCommentLikes(Class<? extends BoardCommentLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardComment = inits.isInitialized("boardComment") ? new com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment(forProperty("boardComment"), inits.get("boardComment")) : null;
        this.user = inits.isInitialized("user") ? new com.dongne.dongnebe.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

