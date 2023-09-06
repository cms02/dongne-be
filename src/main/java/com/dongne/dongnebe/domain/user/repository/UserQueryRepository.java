package com.dongne.dongnebe.domain.user.repository;

import com.dongne.dongnebe.domain.board.dto.FindBestBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindEventBoardsByPeriodDto;
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
import com.dongne.dongnebe.domain.user.dto.UserRankingDto;
import com.dongne.dongnebe.domain.user.dto.request.UserRankingRequestDto;
import com.dongne.dongnebe.domain.user.entity.QUser;
import com.dongne.dongnebe.domain.user.entity.User;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.dongne.dongnebe.domain.user.entity.QUser.user;
import static io.micrometer.common.util.StringUtils.isEmpty;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<User> findUserRanking(UserRankingRequestDto userRankingRequestDto, Pageable pageable) {
        QUser u = user;
        return queryFactory.selectFrom(u)
                .where(nicknameEq(userRankingRequestDto.getNickname()))
                .orderBy(u.point.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression nicknameEq(String nickname) {
        return isEmpty(nickname) ? null : user.nickname.eq(nickname);
    }
}
