package com.dongne.dongnebe.domain.user.dto;

import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Getter
@SuperBuilder
public class UsersBasicResponseDto extends ResponseDto {
    private String nickname;
    private String cityName;
    private String zoneName;
    private String profileImg;

}
