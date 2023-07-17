package com.dongne.dongnebe.domain.comment.board_comment.repository;

import com.dongne.dongnebe.domain.board.dto.FindHotBoardsDto;
import com.dongne.dongnebe.domain.board.dto.request.FindHotBoardsRequestDto;
import com.dongne.dongnebe.domain.board.entity.QBoard;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.comment.board_comment.dto.FindHotBoardCommentsDto;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.board_comment.entity.QBoardComment;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.QBoardCommentLikes;
import com.dongne.dongnebe.domain.likes.board_likes.entity.QBoardLikes;
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
                .where(c.board.boardId.eq(boardId))
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
                        b.createDate.gt(LocalDateTime.now().minusDays(1)))
                )
                .groupBy(c.boardCommentId)
                .orderBy(l.boardCommentLikesId.count().desc())
                .limit(findHotBoardsRequestDto.getDataCount())
                .fetch();
    }

}
