package com.dongne.dongnebe.domain.board.entity;

import com.dongne.dongnebe.domain.board.dto.request.UpdateBoardRequestDto;
import com.dongne.dongnebe.domain.board.enums.BoardType;
import com.dongne.dongnebe.domain.category.channel.entity.Channel;
import com.dongne.dongnebe.domain.category.main_category.entity.MainCategory;
import com.dongne.dongnebe.domain.category.sub_category.entity.SubCategory;
import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.comment.board_comment.entity.BoardComment;
import com.dongne.dongnebe.domain.likes.board_likes.entity.BoardLikes;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board_today_log")
@Getter
@NoArgsConstructor
@DynamicInsert/*@ColumnDefault Default 설정을 위해*/
public class BoardTodayLog extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_today_log_id")
    private Long boardTodayLogId;

    @Column(name = "board_id")
    private Long boardId;
    @Column(name = "user_id")
    private String userId;

    @Builder
    public BoardTodayLog(Long boardId, String userId) {
        this.boardId = boardId;
        this.userId = userId;
    }
}
