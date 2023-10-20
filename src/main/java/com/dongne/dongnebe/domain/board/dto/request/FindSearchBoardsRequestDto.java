package com.dongne.dongnebe.domain.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSearchBoardsRequestDto {
    private String cityCode;
    private String zoneCode;
    private String title;
    private String userId;
    private Long subCategoryId;
}
