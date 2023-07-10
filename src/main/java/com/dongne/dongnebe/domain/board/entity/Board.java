package com.dongne.dongnebe.domain.board.entity;

import com.dongne.dongnebe.domain.board.dto.UpdateBoardRequestDto;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.dongne.dongnebe.global.service.GlobalService.getImgFilePath;

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

    @Column(name = "file_img")
    private String fileImg;

    @Enumerated(EnumType.STRING)
    private BoardType boardType;

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

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    private LocalDateTime deadline_at;

    @Builder
    public Board(Long boardId, String title, String content, String fileImg, BoardType boardType, MainCategory mainCategory, SubCategory subCategory, Channel channel, User user, City city, Zone zone, Boolean isDeleted, LocalDateTime deadline_at) {
        this.boardId = boardId;
        this.title = title;
        this.content = content;
        this.fileImg = fileImg;
        this.boardType = boardType;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.channel = channel;
        this.user = user;
        this.city = city;
        this.zone = zone;
        this.isDeleted = isDeleted;
        this.deadline_at = deadline_at;
    }

    public void update(UpdateBoardRequestDto updateBoardRequestDto, MultipartFile file) {
        this.title = updateBoardRequestDto.getTitle();
        this.content = updateBoardRequestDto.getContent();
        this.boardType = updateBoardRequestDto.getBoardType();
        this.fileImg = getImgFilePath(file);
        this.mainCategory = MainCategory.builder().mainCategoryId(updateBoardRequestDto.getMainCategoryId()).build();
        this.subCategory = SubCategory.builder().subCategoryId(updateBoardRequestDto.getSubCategoryId()).build();
        this.channel = updateBoardRequestDto.getChannelId() == null ?
                null : Channel.builder().channelId(updateBoardRequestDto.getChannelId()).build();
        this.deadline_at = updateBoardRequestDto.getDeadLineAt() == null ?
                null : LocalDateTime.parse(updateBoardRequestDto.getDeadLineAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    public void delete() {
        this.isDeleted = true;
    }
}
