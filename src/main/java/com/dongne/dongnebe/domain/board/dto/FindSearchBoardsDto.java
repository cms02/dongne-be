package com.dongne.dongnebe.domain.board.dto;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSearchBoardsDto {
    private Long boardId;
    private String title;
    private Long boardLikesCount;
    private String channelName;
    private String userId;
    private String nickname;
    private Long point;
    private String createDate;
    private String fileImg;

    public FindSearchBoardsDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.boardLikesCount = (long) board.getBoardLikes().size();
        this.channelName = board.getChannel() == null ? null : board.getChannel().getName();
        this.userId = board.getUser().getUserId();
        this.nickname = board.getUser().getNickname();
        this.point = board.getUser().getPoint();
        this.createDate = GlobalService.formatLocalDateTimeToString(board.getCreateDate());
        this.fileImg = board.getFileImg();
    }

}
