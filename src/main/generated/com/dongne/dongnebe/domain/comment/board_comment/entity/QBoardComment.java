package com.dongne.dongnebe.domain.comment.board_comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardComment is a Querydsl query type for BoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardComment extends EntityPathBase<BoardComment> {

    private static final long serialVersionUID = 264781197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardComment boardComment = new QBoardComment("boardComment");

    public final com.dongne.dongnebe.global.entity.QBaseEntity _super = new com.dongne.dongnebe.global.entity.QBaseEntity(this);

    public final com.dongne.dongnebe.domain.board.entity.QBoard board;

    public final NumberPath<Long> boardCommentId = createNumber("boardCommentId", Long.class);

    public final ListPath<com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes, com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes> boardCommentLikes = this.<com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes, com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes>createList("boardCommentLikes", com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes.class, com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createDate = _super.createDate;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final ListPath<com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment, com.dongne.dongnebe.domain.comment.reply_comment.entity.QReplyComment> replyComments = this.<com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment, com.dongne.dongnebe.domain.comment.reply_comment.entity.QReplyComment>createList("replyComments", com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment.class, com.dongne.dongnebe.domain.comment.reply_comment.entity.QReplyComment.class, PathInits.DIRECT2);

    public final com.dongne.dongnebe.domain.user.entity.QUser user;

    public QBoardComment(String variable) {
        this(BoardComment.class, forVariable(variable), INITS);
    }

    public QBoardComment(Path<? extends BoardComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardComment(PathMetadata metadata, PathInits inits) {
        this(BoardComment.class, metadata, inits);
    }

    public QBoardComment(Class<? extends BoardComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.dongne.dongnebe.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
        this.user = inits.isInitialized("user") ? new com.dongne.dongnebe.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

