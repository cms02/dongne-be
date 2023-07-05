package com.dongne.dongnebe.domain.user.entity;

import com.dongne.dongnebe.domain.city.entity.City;
import com.dongne.dongnebe.domain.user.dto.BasicRequestDto;
import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.domain.zone.entity.Zone;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private String userId;
    private String password;
    private String username;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityCode")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zoneCode")
    private Zone zone;

    @Column(name = "profile_img")
    private String profileImg;
    private Long point;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private Boolean isDeleted = false;

    @Builder
    public User(String userId, String username, String password, String nickname, Role role, City city, Zone zone) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.city = city;
        this.zone = zone;
    }

    public void updateBasic(BasicRequestDto requestDto) {
        this.nickname = requestDto.getNickname();
        this.city = City.builder().cityCode(requestDto.getCityCode()).build();
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
}
