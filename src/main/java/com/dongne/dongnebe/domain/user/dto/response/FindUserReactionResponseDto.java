package com.dongne.dongnebe.domain.user.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindSearchBoardsDto;
import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.user.dto.FindUserReactionDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
public class FindUserReactionResponseDto extends ResponseDto {
    private List<FindUserReactionDto> findUserReactionDtos;
    private int totalPageCount;

    public FindUserReactionResponseDto(List<Board> boardList, int totalPageCount) {
        super(HttpStatus.OK.value(), "Find User Reaction");
        this.findUserReactionDtos = boardList.stream()
                .map(FindUserReactionDto::new)
                .collect(Collectors.toList());
        this.totalPageCount = totalPageCount;
    }
}
