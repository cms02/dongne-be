package com.dongne.dongnebe.domain.user.dto.response;

import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class LoginResponseDto extends ResponseDto {
    private String accessToken;
    private String refreshToken;
}
