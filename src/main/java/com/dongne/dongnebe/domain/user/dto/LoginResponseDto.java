package com.dongne.dongnebe.domain.user.dto;

import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoginResponseDto extends ResponseDto {
    private String accessToken;
    private String refreshToken;
}
