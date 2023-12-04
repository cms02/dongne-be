package com.dongne.dongnebe.domain.likes.board_likes.repository;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    public List<Board> findBoardLikesByBoardIds(List<Long> myBoardIds, Pageable pageable) {
        QBoardLikes l = QBoardLikes.boardLikes;
        QBoard b = QBoard.board;
        return queryFactory
                .select(b)
                .from(l)
                .where(l.board.boardId.in(myBoardIds)
                        .and(b.isDeleted.eq(Boolean.FALSE)))
                .orderBy(l.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public int findBoardLikesSize(List<Long> myBoardIds) {
        QBoardLikes l = QBoardLikes.boardLikes;
        QBoard b = QBoard.board;
        return queryFactory
                .select(b)
                .from(l)
                .where(l.board.boardId.in(myBoardIds)
                        .and(b.isDeleted.eq(Boolean.FALSE)))
                .fetch()
                .size();
    }

    public Optional<BoardLikes> findByBoardIdAndUserId(Long boardId, String userId) {
        QBoardLikes l = QBoardLikes.boardLikes;
        return Optional.ofNullable(queryFactory
                .selectFrom(l)
                .where(l.board.boardId.eq(boardId)
                        .and(l.user.userId.eq(userId)))
                .fetchOne());
    }
}
