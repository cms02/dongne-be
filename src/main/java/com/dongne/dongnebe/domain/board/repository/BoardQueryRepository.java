package com.dongne.dongnebe.domain.board.repository;

import com.dongne.dongnebe.domain.board.dto.FindBestBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindEventBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindHotBoardsDto;
import com.dongne.dongnebe.domain.board.dto.FindSearchBoardsDto;
import com.dongne.dongnebe.domain.board.dto.request.FindBestBoardsRequestDto;
import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.board.dto.request.FindHotBoardsRequestDto;
import com.dongne.dongnebe.domain.board.dto.request.FindSearchBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardsByUserDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.dongne.dongnebe.domain.board.entity.QBoard.board;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Board> findLatestBoards(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        QBoard b = board;
        return queryFactory
                .selectFrom(b)
                .where(
                        b.city.cityCode.eq(findDefaultBoardsRequestDto.getCityCode()).and(
                                b.zone.zoneCode.eq(findDefaultBoardsRequestDto.getZoneCode())))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(sortCondition(pageable))
                .fetch();
    }

    public int findSearchBoardsSize(FindSearchBoardsRequestDto findSearchBoardsRequestDto) {
        QBoard b = board;
        return queryFactory
                .selectFrom(b)
                .where(
                        b.city.cityCode.eq(findSearchBoardsRequestDto.getCityCode()).and(
                                b.zone.zoneCode.eq(findSearchBoardsRequestDto.getZoneCode())))
                .fetch()
                .size();
    }

    public List<FindBestBoardsByPeriodDto> findBestBoardsByPeriod(FindBestBoardsRequestDto findBestBoardsRequestDto, Pageable pageable) {
        QBoard b = board;
        QBoardLikes l = QBoardLikes.boardLikes;
        List<FindBestBoardsByPeriodDto> result = queryFactory
                .select(Projections.fields(FindBestBoardsByPeriodDto.class,
                        b.boardId,
                        b.title,
                        l.count().as("boardLikesCount"),
                        b.channel.name.as("channelName")))
                .from(b)
                .where(
                        b.city.cityCode.eq(findBestBoardsRequestDto.getCityCode()).and(
                                b.zone.zoneCode.eq(findBestBoardsRequestDto.getZoneCode())).and(
                                b.createDate.gt(LocalDateTime.now().minusDays(findBestBoardsRequestDto.getPeriod().getValue()))
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
        QBoard b = board;
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
        QBoard b = board;
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
        QBoard b = board;
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

    public List<FindEventBoardsByPeriodDto> findEventBoardsByPeriod(FindDefaultBoardsRequestDto findDefaultBoardsRequestDto, Pageable pageable) {
        QBoard b = board;
        return queryFactory
                .select(Projections.fields(FindEventBoardsByPeriodDto.class,
                        b.boardId,
                        b.title,
                        b.user.userId,
                        b.user.nickname,
                        b.user.point,
                        b.fileImg,
                        b.viewCnt
                ))
                .from(b)
                .where(b.isDeleted.eq(Boolean.FALSE).and(b.boardType.eq(BoardType.EVENT)).and(b.deadlineAt.after(LocalDateTime.now()))
                        .and(b.city.cityCode.eq(findDefaultBoardsRequestDto.getCityCode()))
                        .and(b.zone.zoneCode.eq(findDefaultBoardsRequestDto.getZoneCode())))
                .orderBy(b.deadlineAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public List<FindSearchBoardsDto> findSearchBoards(FindSearchBoardsRequestDto findSearchBoardsRequestDto, Pageable pageable) {
        QBoard b = board;
        return queryFactory
                .select(Projections.fields(FindSearchBoardsDto.class,
                        b.boardId,
                        b.title,
                        b.boardLikes.size().longValue().as("boardLikesCount"),
                        b.channel.name.as("channelName"),
                        b.user.userId,
                        b.user.nickname,
                        b.user.point,
                        b.createDate,
                        b.fileImg
                        )
                )
                .from(b)
                .where(b.city.cityCode.eq(findSearchBoardsRequestDto.getCityCode())
                        .and(b.zone.zoneCode.eq(findSearchBoardsRequestDto.getZoneCode()))
                        .and(subCategoryIdEq(findSearchBoardsRequestDto.getSubCategoryId()))
                        .and(channelIdEq(findSearchBoardsRequestDto.getChannelId()))
                        .and(titleEq(findSearchBoardsRequestDto.getTitle()))
                        .and(userIdEq(findSearchBoardsRequestDto.getUserId()))
                )
                .orderBy(sortCondition(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression subCategoryIdEq(Long subCategoryId) {
        return subCategoryId == null ? null : board.subCategory.subCategoryId.eq(subCategoryId);
    }

    private BooleanExpression channelIdEq(Long channelId) {
        return channelId == null ? null : board.channel.channelId.eq(channelId);
    }

    private BooleanExpression titleEq(String title) {
        return isEmpty(title) ? null : board.title.eq(title);
    }

    private BooleanExpression userIdEq(String userId) {
        return isEmpty(userId) ? null : board.user.userId.eq(userId);
    }

    private OrderSpecifier<?> sortCondition(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "latest":
                        return new OrderSpecifier(direction, board.createDate);
                    case "likes":
                        return new OrderSpecifier(direction, board.boardLikes.size());
                    case "comments":
                        return new OrderSpecifier(direction, board.boardComments.size());
                }
            }
        }
        return null;
    }
}
