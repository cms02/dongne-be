package com.dongne.dongnebe.domain.likes.board_likes.repository;

import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardLikesQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<BoardLikes> findBoardLikesByBoardIdAndUserId(Long boardId, String userId) {
        QBoardLikes l = QBoardLikes.boardLikes;
        return Optional.ofNullable(queryFactory.selectFrom(l)
                .where(l.board.boardId.eq(boardId).and(l.user.userId.eq(userId)))
                .fetchOne());
    }

}
