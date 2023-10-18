package com.dongne.dongnebe.domain.board.dto;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class FindLatestBoardsDto {
    private Long boardId;
    private String title;
    private Long boardCommentCount;
    private String channelName;
    private String userId;
    private String nickname;
    private Long point;
    private String createDate;
    private Long boardLikesCount;
    private String fileImg;


    public FindLatestBoardsDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.boardCommentCount = (long) board.getBoardComments().size();
        this.channelName = board.getChannel() == null ? null : board.getChannel().getName();
        this.userId = board.getUser().getUserId();
        this.nickname = board.getUser().getNickname();
        this.point = board.getUser().getPoint();
        this.createDate = GlobalService.formatLocalDateTimeToString(board.getCreateDate());
        this.boardLikesCount = (long) board.getBoardLikes().size();
        this.fileImg = board.getFileImg();
    }
}
