package com.dongne.dongnebe.domain.comment.reply_comment.entity;

import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.comment.reply_comment.dto.request.UpdateReplyCommentRequestDto;
import com.dongne.dongnebe.domain.likes.reply_comment_likes.entity.ReplyCommentLikes;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

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

    @Column(columnDefinition = "TEXT")
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

    @OneToMany(mappedBy = "replyComment")
    private List<ReplyCommentLikes> replyCommentLikes = new ArrayList<>();

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
