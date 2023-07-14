package com.dongne.dongnebe.domain.board.dto;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.dongne.dongnebe.domain.board.entity.Board;
import lombok.Getter;

@Getter
public class FindLatestBoardsDto {
    private Long boardId;
    private String title;
    private Long boardCommentCount;
    private String channelName;

    public FindLatestBoardsDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.boardCommentCount = (long) board.getBoardComments().size();
        this.channelName = board.getChannel() == null ? null : board.getChannel().getName();
    }
}
