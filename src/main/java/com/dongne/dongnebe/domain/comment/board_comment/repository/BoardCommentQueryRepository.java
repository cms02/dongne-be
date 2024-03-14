package com.dongne.dongnebe.domain.comment.board_comment.repository;

import com.dongne.dongnebe.domain.board.dto.FindHotBoardsDto;
import com.dongne.dongnebe.domain.board.dto.request.FindHotBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.comment.board_comment.dto.FindHotBoardCommentsDto;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardCommentsByUserDto;
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
public class BoardCommentQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<BoardComment> findBoardComments(Long boardId, Pageable pageable) {
        QBoardComment c = QBoardComment.boardComment;
        return queryFactory
                .selectFrom(c)
                .where(c.board.boardId.eq(boardId).and(
                        c.isDeleted.eq(Boolean.FALSE)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(c.boardCommentId.asc())
                .fetch();
    }

    public List<FindHotBoardCommentsDto> findHotBoardCommentsBySubCategoryId(Long subCategoryId, FindHotBoardsRequestDto findHotBoardsRequestDto) {
        QBoardComment c = QBoardComment.boardComment;
        QBoardCommentLikes l = QBoardCommentLikes.boardCommentLikes;
        QBoard b = QBoard.board;
        return queryFactory.select(Projections.fields(FindHotBoardCommentsDto.class,
                        c.board.boardId,
                        c.boardCommentId,
                        c.content,
                        l.count().as("boardCommentLikesCount")
                ))
                .from(c)
                .join(c.boardCommentLikes, l)
                .join(c.board, b)
                .where(b.subCategory.subCategoryId.eq(subCategoryId).and(
                        b.city.cityCode.eq(findHotBoardsRequestDto.getCityCode())).and(
                        b.zone.zoneCode.eq(findHotBoardsRequestDto.getZoneCode())).and(
                        b.boardType.eq(BoardType.NORMAL)).and(
                        b.createDate.gt(LocalDateTime.now().minusDays(1))).and(
                                c.isDeleted.eq(Boolean.FALSE)
                        )
                )
                .groupBy(c.boardCommentId, c.board.boardId)
                .orderBy(l.boardCommentLikesId.count().desc(),c.board.boardId.desc())
                .limit(findHotBoardsRequestDto.getDataCount())
                .fetch();
    }

    public List<FindLatestBoardCommentsByUserDto> findLatestBoardCommentsByUser(String name, Pageable pageable) {
        QBoardComment c = QBoardComment.boardComment;
        return queryFactory
                .select(Projections.fields(FindLatestBoardCommentsByUserDto.class,
                        c.board.boardId,
                        c.boardCommentId,
                        c.content
                ))
                .from(c)
                .where(c.isDeleted.eq(Boolean.FALSE).and(c.user.userId.eq(name)))
                .orderBy(c.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public List<Board> findBoardCommentsByBoardIds(List<Long> myBoardIds, Pageable pageable) {
        QBoardComment c = QBoardComment.boardComment;
        QBoard b = QBoard.board;
        return queryFactory
                .select(b)
                .from(c)
                .where(c.board.boardId.in(myBoardIds)
                        .and(b.isDeleted.eq(Boolean.FALSE)))
                .orderBy(c.createDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public int findBoardCommentsSize(List<Long> myBoardIds) {
        QBoardComment c = QBoardComment.boardComment;
        QBoard b = QBoard.board;
        return queryFactory
                .select(b)
                .from(c)
                .where(c.board.boardId.in(myBoardIds)
                        .and(b.isDeleted.eq(Boolean.FALSE)))
                .fetch()
                .size();
    }
}
