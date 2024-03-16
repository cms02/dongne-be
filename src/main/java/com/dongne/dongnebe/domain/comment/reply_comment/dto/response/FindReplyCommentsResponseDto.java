package com.dongne.dongnebe.domain.comment.reply_comment.dto.response;

import com.dongne.dongnebe.domain.comment.reply_comment.dto.FindReplyCommentDto;
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
        super(HttpStatus.OK.value(), "Find Reply Comments");
        this.findReplyCommentDtos = findReplyCommentDtos;
    }
}
