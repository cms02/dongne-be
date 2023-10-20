package com.dongne.dongnebe.domain.board.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindSearchBoardsDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
public class FindSearchBoardsResponseDto extends ResponseDto {
    private List<FindSearchBoardsDto> findSearchBoardsDtos;
    private int totalPageCount;

    public FindSearchBoardsResponseDto(List<Board> boardList, int totalPageCount) {
        super(HttpStatus.OK.value(), "Find Search Boards");
        this.findSearchBoardsDtos = boardList.stream()
                .map(FindSearchBoardsDto::new)
                .collect(Collectors.toList());
        this.totalPageCount = totalPageCount;
    }
}
