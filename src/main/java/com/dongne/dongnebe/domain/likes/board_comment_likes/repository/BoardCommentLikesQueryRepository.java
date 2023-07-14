package com.dongne.dongnebe.domain.likes.board_comment_likes.repository;

import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardCommentLikesQueryRepository {
    private final JPAQueryFactory queryFactory;
    public Optional<BoardCommentLikes> findBoardCommentLikesByBoardCommentIdAndUserId(Long boardCommentId, String userId) {
        QBoardCommentLikes l = QBoardCommentLikes.boardCommentLikes;
        return Optional.ofNullable(queryFactory.selectFrom(l)
                .where(l.boardComment.boardCommentId.eq(boardCommentId).and(l.user.userId.eq(userId)))
                .fetchOne());
    }

}
