package com.dongne.dongnebe.domain.comment.board_comment.repository;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardCommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<BoardComment> findBoardComments(Long boardId, Pageable pageable) {
        QBoardComment c = QBoardComment.boardComment;
        return queryFactory
                .selectFrom(c)
                .where(c.board.boardId.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(c.boardCommentId.asc())
                .fetch();
    }
}
