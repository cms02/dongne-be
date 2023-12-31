package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindHotBoardsByCategoriesDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindHotBoardsResponseDto extends ResponseDto {
    List<FindHotBoardsByCategoriesDto> findHotBoardsByCategoriesDtos;

    public FindHotBoardsResponseDto(List<FindHotBoardsByCategoriesDto> findHotBoardsByCategoriesDtos) {
        super(HttpStatus.OK.value(), "Find Hot Boards");
        this.findHotBoardsByCategoriesDtos = findHotBoardsByCategoriesDtos;
    }
}
