package com.dongne.dongnebe.domain.comment.board_comment.dto;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.FindReplyCommentDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter

public class FindBoardCommentDto {
    private Long boardCommentId;
    private String content;
    private String userId;
    private String nickname;
    private Long point;
    private String createDate;
    private Long boardCommentLikesCount;
    private Boolean isLiked;
    private Long replyCommentCount;

    @Builder
    public FindBoardCommentDto(BoardComment boardComment, Boolean isLiked) {
        this.boardCommentId = boardComment.getBoardCommentId();
        this.content = boardComment.getContent();
        this.userId = boardComment.getUser().getUserId();
        this.nickname = boardComment.getUser().getNickname();
        this.point = boardComment.getUser().getPoint();
        this.createDate = formatLocalDateTimeToString(boardComment.getCreateDate());
        this.boardCommentLikesCount = (long) boardComment.getBoardCommentLikes().size();
        this.replyCommentCount = (long) boardComment.getReplyComments().size();
        this.isLiked = isLiked;
    }
}
