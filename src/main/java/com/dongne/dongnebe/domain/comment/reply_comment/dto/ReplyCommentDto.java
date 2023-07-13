package com.dongne.dongnebe.domain.comment.reply_comment.dto;

import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter
public class ReplyCommentDto {
    private Long replyCommentId;
    private String userId;
    private String content;
    private String createDate;

    @Builder
    public ReplyCommentDto(ReplyComment replyComment) {
        this.replyCommentId = replyComment.getReplyCommentId();
        this.userId = replyComment.getUser().getUserId();
        this.content = replyComment.getContent();
        this.createDate = formatLocalDateTimeToString(replyComment.getCreateDate());
    }
}
