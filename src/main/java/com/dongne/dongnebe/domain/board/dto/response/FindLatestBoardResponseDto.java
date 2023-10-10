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
    private List<FindLatestBoardsDto> findLatestBoardsDtos;
    private int totalPageCount;

    public FindLatestBoardResponseDto(List<FindLatestBoardsDto> findLatestBoardsDtos, int totalPageCount) {
        super(HttpStatus.OK.value(), "Find Latest Boards");
        this.findLatestBoardsDtos = findLatestBoardsDtos;
        this.totalPageCount = totalPageCount;
    }
}
