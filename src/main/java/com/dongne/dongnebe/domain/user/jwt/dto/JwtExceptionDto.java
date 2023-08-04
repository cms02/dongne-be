package com.dongne.dongnebe.domain.user.jwt.dto;

import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class JwtExceptionDto extends ResponseDto {
    private String clientRequestUri;
}
