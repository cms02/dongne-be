package com.dongne.dongnebe.domain.user.dto;

import lombok.Getter;

@Getter

public class FindLatestBoardCommentsByUserDto {
    private Long boardId;
    private Long boardCommentId;
    private String content;
}
