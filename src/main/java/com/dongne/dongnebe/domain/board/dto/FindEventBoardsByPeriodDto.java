package com.dongne.dongnebe.domain.board.dto;

import lombok.Getter;

@Getter
public class FindEventBoardsByPeriodDto {
    private Long boardId;
    private String title;
    private String userId;
    private String fileImg;
    private Long viewCnt;
}
