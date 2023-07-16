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

//    @Size(max = 10, message = "7 글자 이내의 닉네임을 입력하세요.")
    private String title;
    private String content;
    private BoardType boardType;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String cityCode;
    private String zoneCode;
    private Long channelId;
    private String deadlineAt;

}
