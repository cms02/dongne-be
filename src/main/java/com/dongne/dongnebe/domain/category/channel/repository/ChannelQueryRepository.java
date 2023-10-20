package com.dongne.dongnebe.domain.category.channel.repository;

import com.dongne.dongnebe.domain.board.dto.FindBestBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindEventBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindHotBoardsDto;
import com.dongne.dongnebe.domain.board.dto.FindSearchBoardsDto;
import com.dongne.dongnebe.domain.board.dto.request.FindDefaultBoardsRequestDto;
import com.dongne.dongnebe.domain.board.dto.request.FindHotBoardsRequestDto;
import com.dongne.dongnebe.domain.board.dto.request.FindSearchBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.channel.entity.QChannel;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.domain.category.sub_category.entity.QSubCategory;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardsByUserDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
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
import static com.dongne.dongnebe.domain.category.channel.entity.QChannel.channel;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class ChannelQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Channel findChannelBySubCategoryIdAndName(Long subCategoryId, String channelName) {
        QChannel c = channel;
        return queryFactory
                .selectFrom(c)
                .where(
                        c.subCategory.subCategoryId.eq(subCategoryId).and(
                                c.name.eq(channelName)
                        )
                )
                .fetchOne();
    }

}
