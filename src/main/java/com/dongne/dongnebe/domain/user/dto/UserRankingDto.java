package com.dongne.dongnebe.domain.user.dto;

import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.service.GlobalService;
import lombok.Getter;

@Getter
public class UserRankingDto {
    private String userId;
    private String nickname;
    private String profileImg;
    private Long point;
    private String createDate;

    public UserRankingDto(User user) {
        userId = user.getUserId();
        nickname = user.getNickname();
        profileImg = user.getProfileImg();
        point = user.getPoint();
        createDate = GlobalService.formatLocalDateTimeToString(user.getCreateDate());
    }
}
