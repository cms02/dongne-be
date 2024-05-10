package com.dongne.dongnebe.domain.board.repository;

import com.dongne.dongnebe.domain.board.entity.BoardTodayLog;
import com.dongne.dongnebe.domain.board.entity.QBoardTodayLog;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardTodayLogQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<BoardTodayLog> findByBoardIdAndUserId(Long boardId, String userId) {
        QBoardTodayLog b = QBoardTodayLog.boardTodayLog;
        return queryFactory
                .selectFrom(b)
                .where(
                        b.boardId.eq(boardId).and(b.userId.eq(userId)))
                .fetch();
    }

}
