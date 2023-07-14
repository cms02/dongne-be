package com.dongne.dongnebe.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindLatestBoardsRequestDto {
    private String cityCode;
    private String zoneCode;
}
