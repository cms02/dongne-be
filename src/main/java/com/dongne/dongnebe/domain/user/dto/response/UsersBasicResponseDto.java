package com.dongne.dongnebe.domain.user.dto.response;

import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
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

    public UsersBasicResponseDto(User user) {
        super(HttpStatus.OK.value(), "Find Users Basic");
        this.nickname = user.getNickname();
        this.cityName = user.getCity().getName();
        this.zoneName = user.getZone().getName();
        this.profileImg = user.getProfileImg();
    }
}
