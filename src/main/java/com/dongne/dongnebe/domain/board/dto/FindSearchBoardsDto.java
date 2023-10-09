package com.dongne.dongnebe.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSearchBoardsDto {
    private Long boardId;
    private String title;
    private Long boardLikesCount;
    private String channelName;

}
