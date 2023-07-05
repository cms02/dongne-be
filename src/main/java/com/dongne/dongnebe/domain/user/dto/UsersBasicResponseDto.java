package com.dongne.dongnebe.domain.user.dto;

import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UsersBasicResponseDto extends ResponseDto {
    private String nickname;
    private String cityName;
    private String ZoneName;
    private String profile_img;
}
