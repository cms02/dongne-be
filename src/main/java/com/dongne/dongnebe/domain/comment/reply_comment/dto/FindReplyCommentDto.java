package com.dongne.dongnebe.domain.comment.reply_comment.dto;

import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import lombok.Builder;
import lombok.Getter;

import static com.dongne.dongnebe.global.service.GlobalService.formatLocalDateTimeToString;

@Getter

public class FindReplyCommentDto {
    private Long replyCommentId;
    private String content;
    private String userId;
    private String nickname;
    private Long point;
    private String createDate;
    private Boolean isLiked;
    private Long replyCommentLikesCount;
    private Long replyCommentLikesId;

    @Builder
    public FindReplyCommentDto(ReplyComment replyComment, Long replyCommentLikesId) {
        this.replyCommentId = replyComment.getReplyCommentId();
        this.content = replyComment.getContent();
        this.userId = replyComment.getUser().getUserId();
        this.nickname = replyComment.getUser().getNickname();
        this.point = replyComment.getUser().getPoint();
        this.createDate = formatLocalDateTimeToString(replyComment.getCreateDate());
        this.isLiked = replyCommentLikesId == null? false : true;
        this.replyCommentLikesId = replyCommentLikesId;
        this.replyCommentLikesCount = replyComment.getReplyCommentLikes().stream().count();
    }
}
