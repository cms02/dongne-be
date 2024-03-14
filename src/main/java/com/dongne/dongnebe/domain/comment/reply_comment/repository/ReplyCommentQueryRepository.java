package com.dongne.dongnebe.domain.comment.reply_comment.repository;

import com.dongne.dongnebe.domain.comment.reply_comment.entity.QReplyComment;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyCommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<ReplyComment> findReplyComments(Long boardCommentId, Pageable pageable) {
        QReplyComment c = QReplyComment.replyComment;
        return queryFactory
                .selectFrom(c)
                .where(c.boardComment.boardCommentId.eq(boardCommentId).and(
                        c.isDeleted.eq(Boolean.FALSE)
                ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(c.replyCommentId.asc())
                .fetch();
    }

}
