package com.dongne.dongnebe.domain.user.dto.response;

import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UsersBasicResponseDto extends ResponseDto {
    private String nickname;
    private String cityName;
    private String zoneName;
    private String profileImg;

}
