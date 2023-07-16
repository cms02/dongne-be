package com.dongne.dongnebe.domain.comment.board_comment.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.comment.board_comment.dto.request.UpdateBoardCommentRequestDto;
import com.dongne.dongnebe.domain.comment.reply_comment.entity.ReplyComment;
import com.dongne.dongnebe.domain.likes.board_comment_likes.entity.BoardCommentLikes;
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
@Table(name = "board_comment")
@Getter
@NoArgsConstructor
@DynamicInsert/*@ColumnDefault Default 설정을 위해*/
public class BoardComment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_comment_id")
    private Long boardCommentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "boardComment")
    private List<ReplyComment> replyComments = new ArrayList<>();

    @OneToMany(mappedBy = "boardComment")
    private List<BoardCommentLikes> boardCommentLikes = new ArrayList<>();

    @Builder
    public BoardComment(Long boardCommentId, String content, Board board, User user, Boolean isDeleted) {
        this.boardCommentId = boardCommentId;
        this.content = content;
        this.board = board;
        this.user = user;
        this.isDeleted = isDeleted;
    }

    public void update(UpdateBoardCommentRequestDto updateBoardCommentRequestDto) {
        this.content = updateBoardCommentRequestDto.getContent();
    }

    public void delete() {
        this.isDeleted = true;
    }
}
