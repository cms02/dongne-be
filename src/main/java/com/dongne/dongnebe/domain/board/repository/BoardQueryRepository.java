package com.dongne.dongnebe.domain.board.repository;

import com.dongne.dongnebe.domain.board.dto.FindTodayBestBoardsDto;
import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Board> findLatestBoards(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        QBoard b = QBoard.board;
        return queryFactory
                .selectFrom(b)
                .where(
                        b.city.cityCode.eq(findDefaultBoardsRequestDto.getCityCode()).and(
                        b.zone.zoneCode.eq(findDefaultBoardsRequestDto.getZoneCode())))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(b.createDate.desc(), b.boardId.desc())
                .fetch();
    }

    public List<FindTodayBestBoardsDto> findTodayBestBoards(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        QBoard b = QBoard.board;
        QBoardLikes l = QBoardLikes.boardLikes;
        List<FindTodayBestBoardsDto> result = queryFactory
                .select(Projections.fields(FindTodayBestBoardsDto.class,
                        b.boardId,
                        b.title,
                        l.count().as("boardLikesCount"),
                        b.channel.name.as("channelName")))
                .from(b)
                .where(
                        b.city.cityCode.eq(findDefaultBoardsRequestDto.getCityCode()).and(
                                b.zone.zoneCode.eq(findDefaultBoardsRequestDto.getZoneCode())))
                .join(b.boardLikes, l)
                .groupBy(b.boardId)
                .orderBy(l.count().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }
}
