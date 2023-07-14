package com.dongne.dongnebe.domain.comment.board_comment.dto;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
public class FindBoardCommentsResponseDto extends ResponseDto {
    private List<FindBoardCommentDto> findBoardCommentDtos;

    public FindBoardCommentsResponseDto(List<FindBoardCommentDto> findBoardCommentDtos) {
        super(HttpStatus.OK.value(), "Find Board Comments");
        this.findBoardCommentDtos = findBoardCommentDtos;
    }
}
