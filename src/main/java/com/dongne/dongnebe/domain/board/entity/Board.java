package com.dongne.dongnebe.domain.board.entity;

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
@Table(name = "board")
@Getter
@NoArgsConstructor
@DynamicInsert/*@ColumnDefault Default 설정을 위해*/
public class Board extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long boardId;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_code")
    private Zone zone;

    @ColumnDefault("0")
    private Long likes;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    private LocalDateTime deadline_at;

    @Builder
    public Board(String title, String content, BoardType type, MainCategory mainCategory, SubCategory subCategory, Channel channel, User user, City city, Zone zone, Long likes, Boolean isDeleted, LocalDateTime deadline_at) {
        this.title = title;
        this.content = content;
        this.type = type;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.channel = channel;
        this.user = user;
        this.city = city;
        this.zone = zone;
        this.likes = likes;
        this.isDeleted = isDeleted;
        this.deadline_at = deadline_at;
    }
}
