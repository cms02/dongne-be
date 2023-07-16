package com.dongne.dongnebe.domain.comment.reply_comment.dto;

import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindReplyCommentsResponseDto extends ResponseDto {
    private List<FindReplyCommentDto> findReplyCommentDtos;

    public FindReplyCommentsResponseDto(List<FindReplyCommentDto> findReplyCommentDtos) {
        super(HttpStatus.OK.value(), "Find Board Comments");
        this.findReplyCommentDtos = findReplyCommentDtos;
    }
}
