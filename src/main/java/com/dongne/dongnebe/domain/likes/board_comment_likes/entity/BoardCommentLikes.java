package com.dongne.dongnebe.domain.likes.board_comment_likes.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_comment_likes")
@Getter
@NoArgsConstructor
public class BoardCommentLikes {

    @Id
    @GeneratedValue
    @Column(name = "board_comment_likes_id")
    private Long boardCommentLikesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_comment_id")
    private BoardComment boardComment;
}
