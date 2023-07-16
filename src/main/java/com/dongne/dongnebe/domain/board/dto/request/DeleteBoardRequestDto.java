package com.dongne.dongnebe.domain.board.dto.request;

import com.dongne.dongnebe.domain.board.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteBoardRequestDto {

    private String userId;

}
