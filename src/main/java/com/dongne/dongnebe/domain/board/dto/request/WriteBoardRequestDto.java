package com.dongne.dongnebe.domain.board.dto.request;

import com.dongne.dongnebe.domain.board.enums.BoardType;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteBoardRequestDto {

    private String title;
    private String content;
    private BoardType boardType;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String cityCode;
    private String zoneCode;
    private String channelName;
    private String deadlineAt;
    private String fileImg;

}
