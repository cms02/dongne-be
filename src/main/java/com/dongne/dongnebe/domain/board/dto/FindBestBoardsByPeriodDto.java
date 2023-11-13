package com.dongne.dongnebe.domain.board.dto;

import com.dongne.dongnebe.domain.board.entity.Board;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindBestBoardsByPeriodDto {
    private Long boardId;
    private String title;
    private Long boardLikesCount;
    private String channelName;
    private Long boardCommentCount;

    public FindBestBoardsByPeriodDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.boardLikesCount = (long) board.getBoardLikes().size();
        this.channelName = board.getChannel() == null ? null : board.getChannel().getName();;
        this.boardCommentCount = (long) board.getBoardComments().size();
    }
}
