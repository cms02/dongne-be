package com.dongne.dongnebe.domain.comment.board_comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteBoardCommentRequestDto {
    private Long boardId;
    private String content;
    private String userId;
}
