package com.dongne.dongnebe.domain.likes.reply_comment_likes.repository;

import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.entity.QReplyCommentLikes;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.entity.ReplyCommentLikes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyCommentLikesQueryRepository {
    private final JPAQueryFactory queryFactory;
    public Optional<ReplyCommentLikes> findBoardCommentLikesByBoardCommentIdAndUserId(Long replyCommentId, String userId) {
        QReplyCommentLikes l = QReplyCommentLikes.replyCommentLikes;
        return Optional.ofNullable(queryFactory.selectFrom(l)
                .where(l.replyComment.replyCommentId.eq(replyCommentId).and(l.user.userId.eq(userId)))
                .fetchOne());
    }

}
