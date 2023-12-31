package com.dongne.dongnebe.domain.likes.board_likes.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board_likes")
@Getter
@NoArgsConstructor
public class BoardLikes extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_likes_id")
    private Long boardLikesId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardLikes(Long boardLikesId, User user, Board board) {
        this.boardLikesId = boardLikesId;
        this.user = user;
        this.board = board;
    }
}
