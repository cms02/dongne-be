package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindBestBoardsByPeriodDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindBestBoardsByPeriodResponseDto extends ResponseDto {
    List<FindBestBoardsByPeriodDto> findBestBoardsByPeriodDtos;

    public FindBestBoardsByPeriodResponseDto(List<FindBestBoardsByPeriodDto> findBestBoardsByPeriodDtos) {
        super(HttpStatus.OK.value(), "Find Best Boards");
        this.findBestBoardsByPeriodDtos = findBestBoardsByPeriodDtos;
    }
}
