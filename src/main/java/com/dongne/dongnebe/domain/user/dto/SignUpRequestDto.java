package com.dongne.dongnebe.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpRequestDto {
    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String address;
}
