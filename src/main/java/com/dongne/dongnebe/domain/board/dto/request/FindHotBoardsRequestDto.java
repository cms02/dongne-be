package com.dongne.dongnebe.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindHotBoardsRequestDto {
    private String cityCode;
    private String zoneCode;
    private Long categoryCount;
    private Long dataCount;
}
