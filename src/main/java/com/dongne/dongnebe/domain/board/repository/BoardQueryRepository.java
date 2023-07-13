package com.dongne.dongnebe.domain.board.repository;

import com.dongne.dongnebe.domain.board.dto.FindMainBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Board> findLatestBoards(FindMainBoardsRequestDto mainBoardsRequestDto, Pageable pageable) {
        QBoard b = QBoard.board;
        return queryFactory
                .selectFrom(b)
                .where(
                        b.city.cityCode.eq(mainBoardsRequestDto.getCityCode()).and(
                        b.zone.zoneCode.eq(mainBoardsRequestDto.getZoneCode())))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(b.createDate.desc(), b.boardId.desc())
                .fetch();
    }

}
