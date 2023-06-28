package com.dongne.dongnebe.domain.user.dto;

import lombok.Getter;

@Getter
public class JoinRequestDto {
    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String address;
}
