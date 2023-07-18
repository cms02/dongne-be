package com.dongne.dongnebe.domain.board.repository;

import com.dongne.dongnebe.domain.board.dto.FindBestBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindHotBoardsDto;
import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.board.dto.request.FindHotBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardsByUserDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<FindBestBoardsByPeriodDto> findBestBoardsByPeriod(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        QBoard b = QBoard.board;
        QBoardLikes l = QBoardLikes.boardLikes;
        List<FindBestBoardsByPeriodDto> result = queryFactory
                .select(Projections.fields(FindBestBoardsByPeriodDto.class,
                        b.boardId,
                        b.title,
                        l.count().as("boardLikesCount"),
                        b.channel.name.as("channelName")))
                .from(b)
                .where(
                        b.city.cityCode.eq(findDefaultBoardsRequestDto.getCityCode()).and(
                                b.zone.zoneCode.eq(findDefaultBoardsRequestDto.getZoneCode())).and(
                                b.createDate.gt(LocalDateTime.now().minusDays(findDefaultBoardsRequestDto.getPeriod().getValue()))
                        ))
                .join(b.boardLikes, l)
                .groupBy(b.boardId, b.channel.name)
                .orderBy(l.count().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return result;
    }

    public List<SubCategoryDto> findTopNSubCategoryIds(FindHotBoardsRequestDto findHotBoardsRequestDto) {
        QBoard b = QBoard.board;
        QBoardLikes l = QBoardLikes.boardLikes;
        QSubCategory s = QSubCategory.subCategory;
        List<SubCategoryDto> result = queryFactory.select(Projections.fields(SubCategoryDto.class,
                        s.subCategoryId, s.name))
                .from(s)
                .join(b).on(s.subCategoryId.eq(b.subCategory.subCategoryId))
                .join(l).on(l.board.boardId.eq(b.boardId))
                .where(b.city.cityCode.eq(findHotBoardsRequestDto.getCityCode()).and(
                        b.zone.zoneCode.eq(findHotBoardsRequestDto.getZoneCode())).and(
                        b.boardType.eq(BoardType.NORMAL)).and(
                        b.createDate.gt(LocalDateTime.now().minusDays(1)))
                )
                .groupBy(s.subCategoryId)
                .orderBy(b.viewCnt.sum().add(l.boardLikesId.count()).desc())
                .limit(findHotBoardsRequestDto.getCategoryCount())
                .fetch();
        return result;
    }

    public List<FindHotBoardsDto> findHotBoardsBySubCategoryId(long subCategoryId, FindHotBoardsRequestDto findHotBoardsRequestDto) {
        QBoard b = QBoard.board;
        QBoardLikes l = QBoardLikes.boardLikes;
        return queryFactory.select(Projections.fields(FindHotBoardsDto.class,
                        b.boardId,
                        b.title,
                        l.count().as("boardLikesCount")
                ))
                .from(b)
                .join(b.boardLikes, l)
                .where(b.subCategory.subCategoryId.eq(subCategoryId).and(
                        b.city.cityCode.eq(findHotBoardsRequestDto.getCityCode())).and(
                        b.zone.zoneCode.eq(findHotBoardsRequestDto.getZoneCode())).and(
                        b.boardType.eq(BoardType.NORMAL)).and(
                        b.createDate.gt(LocalDateTime.now().minusDays(1)))
                )
                .groupBy(b.boardId)
                .orderBy(b.viewCnt.sum().add(l.boardLikesId.count()).desc(),b.boardId.desc())
                .limit(findHotBoardsRequestDto.getDataCount())
                .fetch();
    }

    public List<FindLatestBoardsByUserDto> findLatestBoardsByUser(String name, Pageable pageable) {
        QBoard b = QBoard.board;
        return queryFactory
                .select(Projections.fields(FindLatestBoardsByUserDto.class,
                        b.boardId,
                        b.title
                ))
                .from(b)
                .where(b.isDeleted.eq(Boolean.FALSE).and(b.user.userId.eq(name)))
                .orderBy(b.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

}
