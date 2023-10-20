package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindHotBoardsByCategoriesDto;
import com.dongne.dongnebe.domain.board.dto.FindSearchBoardsDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindSearchBoardsResponseDto extends ResponseDto {
    private List<FindSearchBoardsDto> findSearchBoardsDtos;
    private int totalPageCount;

    public FindSearchBoardsResponseDto(List<FindSearchBoardsDto> findSearchBoardsDtos, int totalPageCount) {
        super(HttpStatus.OK.value(), "Find Search Boards");
        this.findSearchBoardsDtos = findSearchBoardsDtos;
        this.totalPageCount = totalPageCount;
    }
}
