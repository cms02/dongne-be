package com.dongne.dongnebe.domain.board.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindHotBoardsByCategoriesDto {
    private Long subCategoryId;
    private String subCategoryName;
    private List<FindHotBoardsDto> findHotBoardsDtos;
}
