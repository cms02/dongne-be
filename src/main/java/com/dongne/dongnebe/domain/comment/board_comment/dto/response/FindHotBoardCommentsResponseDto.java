package com.dongne.dongnebe.domain.comment.board_comment.dto.response;

import com.dongne.dongnebe.domain.comment.board_comment.dto.FindHotBoardCommentsByCategoriesDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindHotBoardCommentsResponseDto extends ResponseDto {
    List<FindHotBoardCommentsByCategoriesDto> findHotBoardCommentsByCategoriesDtos;

    public FindHotBoardCommentsResponseDto(List<FindHotBoardCommentsByCategoriesDto> findHotBoardCommentsByCategoriesDtos) {
        super(HttpStatus.OK.value(), "Find Hot Board Comments");
        this.findHotBoardCommentsByCategoriesDtos = findHotBoardCommentsByCategoriesDtos;
    }
}
