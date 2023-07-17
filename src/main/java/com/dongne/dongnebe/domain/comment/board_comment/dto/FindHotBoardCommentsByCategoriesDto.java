package com.dongne.dongnebe.domain.comment.board_comment.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FindHotBoardCommentsByCategoriesDto {
    private Long subCategoryId;
    private String subCategoryName;
    private List<FindHotBoardCommentsDto> findHotBoardCommentsDtos;
}
