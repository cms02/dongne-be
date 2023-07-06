package com.dongne.dongnebe.domain.likes.reply_comment_likes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReplyCommentLikes is a Querydsl query type for ReplyCommentLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReplyCommentLikes extends EntityPathBase<ReplyCommentLikes> {

    private static final long serialVersionUID = 924255893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReplyCommentLikes replyCommentLikes = new QReplyCommentLikes("replyCommentLikes");

    public final com.dongne.dongnebe.domain.comment.reply_comment.entity.QReplyComment replyComment;

    public final NumberPath<Long> replyCommentLikesId = createNumber("replyCommentLikesId", Long.class);

    public final com.dongne.dongnebe.domain.user.entity.QUser user;

    public QReplyCommentLikes(String variable) {
        this(ReplyCommentLikes.class, forVariable(variable), INITS);
    }

    public QReplyCommentLikes(Path<? extends ReplyCommentLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReplyCommentLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReplyCommentLikes(PathMetadata metadata, PathInits inits) {
        this(ReplyCommentLikes.class, metadata, inits);
    }

    public QReplyCommentLikes(Class<? extends ReplyCommentLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.replyComment = inits.isInitialized("replyComment") ? new com.dongne.dongnebe.domain.comment.reply_comment.entity.QReplyComment(forProperty("replyComment"), inits.get("replyComment")) : null;
        this.user = inits.isInitialized("user") ? new com.dongne.dongnebe.domain.user.entity.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

