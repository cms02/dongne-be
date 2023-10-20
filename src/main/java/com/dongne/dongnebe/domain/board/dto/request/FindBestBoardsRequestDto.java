package com.dongne.dongnebe.domain.board.dto.request;

import com.dongne.dongnebe.domain.board.enums.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindBestBoardsRequestDto {
    private String cityCode;
    private String zoneCode;
    private Period period;
}
