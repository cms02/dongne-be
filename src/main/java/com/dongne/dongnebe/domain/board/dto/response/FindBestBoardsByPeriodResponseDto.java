package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindBestBoardsByPeriodDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
public class FindBestBoardsByPeriodResponseDto extends ResponseDto {
    List<FindBestBoardsByPeriodDto> findBestBoardsByPeriodDtos;

    public FindBestBoardsByPeriodResponseDto(List<Board> boardList) {
        super(HttpStatus.OK.value(), "Find Best Boards");
        List<FindBestBoardsByPeriodDto> list = new ArrayList<>();
        for (Board board : boardList) {
            FindBestBoardsByPeriodDto findBestBoardsByPeriodDto = new FindBestBoardsByPeriodDto(board);
            list.add(findBestBoardsByPeriodDto);
        }
        this.findBestBoardsByPeriodDtos = list;
    }
}
