package com.dongne.dongnebe.domain.comment.board_comment.dto.response;

import com.dongne.dongnebe.domain.comment.board_comment.dto.FindBoardCommentDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindBoardCommentsResponseDto extends ResponseDto {
    private List<FindBoardCommentDto> findBoardCommentDtos;

    public FindBoardCommentsResponseDto(List<FindBoardCommentDto> findBoardCommentDtos) {
        super(HttpStatus.OK.value(), "Find Board Comments");
        this.findBoardCommentDtos = findBoardCommentDtos;
    }
}
