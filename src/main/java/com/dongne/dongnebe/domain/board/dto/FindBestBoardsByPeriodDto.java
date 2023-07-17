package com.dongne.dongnebe.domain.board.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindBestBoardsByPeriodDto {
    private Long boardId;
    private String title;
    private Long boardLikesCount;
    private String channelName;

}
