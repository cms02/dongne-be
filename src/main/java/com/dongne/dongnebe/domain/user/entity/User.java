package com.dongne.dongnebe.domain.user.entity;

import com.dongne.dongnebe.domain.user.enums.Role;
import com.dongne.dongnebe.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String username;
    private String password;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String address;

    @Column(name = "profile_img")
    private String profileImg;
    private Long point;

    @Column(name = "is_deleted")
    @ColumnDefault("false")
    private boolean isDeleted;

    @Builder
    public User(String userId, String username, String password, String nickname, Role role, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.address = address;
    }
}
