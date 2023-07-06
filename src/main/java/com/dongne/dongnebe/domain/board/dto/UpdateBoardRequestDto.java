package com.dongne.dongnebe.domain.board.dto;

import com.dongne.dongnebe.domain.board.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBoardRequestDto {

//    @Size(max = 10, message = "7 글자 이내의 닉네임을 입력하세요.")
    private String title;
    private String content;
    private BoardType boardType;
    private Long mainCategoryId;
    private Long subCategoryId;
    private Long channelId;
    private String userId;
    private String deadLineAt;

}
