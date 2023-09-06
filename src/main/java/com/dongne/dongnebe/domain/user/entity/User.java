package com.dongne.dongnebe.domain.user.entity;

import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.user.dto.request.BasicRequestDto;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@DynamicInsert /*@ColumnDefault Default 설정을 위해*/
public class User extends BaseEntity {

    final static int BOARD_POINT = 100;
    final static int COMMENT_POINT = 50;
    final static int LIKE_POINT = 20;

    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String username;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_code")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_code")
    private Zone zone;

    @Column(name = "profile_img")
    private String profileImg;

    @ColumnDefault("0")
    private Long point;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted;

    @Builder
    public User(String userId, String username, String password, String nickname, Role role, City city, Zone zone, Long point) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.city = city;
        this.zone = zone;
        this.point = point;
    }

    public void updateBasic(BasicRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.city = City.builder().cityCode(requestDto.getCityCode()).build();
        this.zone = Zone.builder().zoneCode(requestDto.getZoneCode()).build();
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateProfileImg(String imgFilePath) {
        this.profileImg = imgFilePath;
    }

    public void plusPointByBoard() {
        this.point += BOARD_POINT;
    }

    public void plusPointByComment() {
        this.point += COMMENT_POINT;
    }
    public void plusPointByLike() {
        this.point += LIKE_POINT;
    }

    public void minusPointByLike() {
        this.point -= LIKE_POINT;
    }
}
