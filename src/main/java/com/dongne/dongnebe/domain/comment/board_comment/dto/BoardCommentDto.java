package com.dongne.dongnebe.domain.comment.board_comment.dto;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.ReplyCommentDto;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter

public class BoardCommentDto {
    private Long boardCommentId;
    private String content;
    private String userId;
    private String createDate;
    private List<ReplyCommentDto> replyCommentDtos;

    @Builder
    public BoardCommentDto(BoardComment boardComment) {
        this.boardCommentId = boardComment.getBoardCommentId();
        this.content = boardComment.getContent();
        this.userId = boardComment.getUser().getUserId();
        this.createDate = formatLocalDateTimeToString(boardComment.getCreateDate());
        this.replyCommentDtos = boardComment.getReplyComments().stream().map(ReplyCommentDto::new).collect(Collectors.toList());
    }
}
