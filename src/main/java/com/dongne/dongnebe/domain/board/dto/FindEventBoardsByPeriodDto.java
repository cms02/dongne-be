package com.dongne.dongnebe.domain.board.dto;

import com.dongne.dongnebe.domain.board.entity.Board;
import lombok.Getter;

@Getter
public class FindEventBoardsByPeriodDto {
    private Long boardId;
    private String title;
    private String userId;
    private String nickname;
    private Long point;
    private String fileImg;
    private Long viewCnt;
    private Long boardCommentCount;

    public FindEventBoardsByPeriodDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.userId = board.getUser().getUserId();
        this.nickname = board.getUser().getNickname();
        this.point = board.getUser().getPoint();
        this.fileImg = board.getFileImg();
        this.viewCnt = board.getViewCnt();
        this.boardCommentCount = (long) board.getBoardComments().size();
    }
}
