package com.dongne.dongnebe.domain.likes.reply_comment_likes.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reply_comment_likes")
@Getter
@NoArgsConstructor
public class ReplyCommentLikes {

    @Id
    @GeneratedValue
    @Column(name = "reply_comment_likes_id")
    private Long replyCommentLikesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_comment_id")
    private ReplyComment replyComment;
}
