package com.dongne.dongnebe.domain.board.dto;

import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindLatestBoardResponseDto extends ResponseDto {
    List<FindLatestBoardsDto> latestBoardsDtos;

    public FindLatestBoardResponseDto(List<FindLatestBoardsDto> findLatestBoardsDtos) {
        super(HttpStatus.OK.value(), "Find Latest Boards");
        this.latestBoardsDtos = findLatestBoardsDtos;
    }
}
