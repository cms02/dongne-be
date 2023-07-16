package com.dongne.dongnebe.domain.comment.board_comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardCommentRequestDto {
    private String userId;
}
