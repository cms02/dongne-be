package com.dongne.dongnebe.domain.comment.board_comment.repository;

import com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardCommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Long findBoardCommentCount() {
//        QBoardComment q = QBoardComment.boardComment;
//        Long  = queryFactory.selectFrom(q.count())
//                .fetchOne();
//
        return null;
    }
}
