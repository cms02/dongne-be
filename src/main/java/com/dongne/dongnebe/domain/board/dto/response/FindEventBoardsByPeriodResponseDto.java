package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindEventBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
public class FindEventBoardsByPeriodResponseDto extends ResponseDto {

    List<FindEventBoardsByPeriodDto> findEventBoardsByPeriodDtos;

    public FindEventBoardsByPeriodResponseDto(List<Board> boards) {
        super(HttpStatus.OK.value(), "Find Event Boards");
        this.findEventBoardsByPeriodDtos = boards.stream().map(FindEventBoardsByPeriodDto::new).collect(Collectors.toList());
    }

}
