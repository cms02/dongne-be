package com.dongne.dongnebe.domain.comment.board_comment.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

@Entity
@Table(name = "board_comment")
@Getter
@NoArgsConstructor
@DynamicInsert/*@ColumnDefault Default 설정을 위해*/
public class BoardComment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long commentId;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ColumnDefault("0")
    private Long likes;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Builder
    public BoardComment(Long commentId, String content, Board board, User user, Long likes, Boolean isDeleted) {
        this.commentId = commentId;
        this.content = content;
        this.board = board;
        this.user = user;
        this.likes = likes;
        this.isDeleted = isDeleted;
    }
}
