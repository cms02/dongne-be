package com.dongne.dongnebe.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindHotBoardsDto {
    private Long boardId;
    private String title;
    private Long boardLikesCount;
}
