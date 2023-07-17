package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindLatestBoardsDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindLatestBoardResponseDto extends ResponseDto {
    List<FindLatestBoardsDto> findLatestBoardsDtos;

    public FindLatestBoardResponseDto(List<FindLatestBoardsDto> findLatestBoardsDtos) {
        super(HttpStatus.OK.value(), "Find Latest Boards");
        this.findLatestBoardsDtos = findLatestBoardsDtos;
    }
}
