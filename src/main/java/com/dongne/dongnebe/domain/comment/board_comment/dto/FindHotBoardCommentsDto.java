package com.dongne.dongnebe.domain.comment.board_comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindHotBoardCommentsDto {
    private Long boardId;
    private Long boardCommentId;
    private String content;
    private Long boardCommentLikesCount;
}
