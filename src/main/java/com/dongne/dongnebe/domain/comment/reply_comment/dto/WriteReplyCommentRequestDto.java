package com.dongne.dongnebe.domain.comment.reply_comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WriteReplyCommentRequestDto {
    private Long replyCommentId;
    private String content;
}
