package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindEventBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.dto.FindLatestBoardsDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindEventBoardsByPeriodResponseDto extends ResponseDto {

    List<FindEventBoardsByPeriodDto> findEventBoardsByPeriodDtos;

    public FindEventBoardsByPeriodResponseDto(List<FindEventBoardsByPeriodDto> findEventBoardsByPeriodDtos) {
        super(HttpStatus.OK.value(), "Find Event Boards");
        this.findEventBoardsByPeriodDtos = findEventBoardsByPeriodDtos;
    }

}
