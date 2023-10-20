package com.dongne.dongnebe.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindSearchBoardsDto {
    private Long boardId;
    private String title;
    private Long boardLikesCount;
    private String channelName;
    private String userId;
    private String nickname;
    private Long point;
    private LocalDateTime createDate;
    private String fileImg;

}
