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
    private String cityCode;
    private String zoneName;
    private String zoneCode;
    private String profileImg;
    private Long point;

    public UsersBasicResponseDto(User user) {
        super(HttpStatus.OK.value(), "Find Users Basic");
        this.nickname = user.getNickname();
        this.cityName = user.getCity().getName();
        this.cityCode = user.getCity().getCityCode();
        this.zoneCode = user.getZone().getZoneCode();
        this.zoneName = user.getZone().getName();
        this.profileImg = user.getProfileImg();
        this.point = user.getPoint();
    }
}
