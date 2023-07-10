package com.dongne.dongnebe.domain.comment.reply_comment.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.UpdateReplyCommentRequestDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "reply_comment")
@Getter
@NoArgsConstructor
@DynamicInsert/*@ColumnDefault Default 설정을 위해*/
public class ReplyComment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "reply_comment_id")
    private Long replyCommentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_comment_id")
    private BoardComment boardComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Builder
    public ReplyComment(Long replyCommentId, String content, BoardComment boardComment, User user, Boolean isDeleted) {
        this.replyCommentId = replyCommentId;
        this.content = content;
        this.boardComment = boardComment;
        this.user = user;
        this.isDeleted = isDeleted;
    }

    public void update(UpdateReplyCommentRequestDto updateReplyCommentRequestDto) {
        this.content = updateReplyCommentRequestDto.getContent();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
