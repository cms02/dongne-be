package com.dongne.dongnebe.domain.user.dto.response;

import com.dongne.dongnebe.domain.user.dto.FindLatestBoardCommentsByUserDto;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardsByUserDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class UsersMainResponseDto extends ResponseDto {
    private String userId;
    private String nickname;
    private String cityName;
    private String zoneName;
    private Long point;
    private String profileImg;
    private List<FindLatestBoardsByUserDto> findLatestBoardsByUserDtos;
    private List<FindLatestBoardCommentsByUserDto> findLatestBoardCommentsByUserDtos;

    public UsersMainResponseDto(User user, List<FindLatestBoardsByUserDto> findLatestBoardsByUserDtos, List<FindLatestBoardCommentsByUserDto> findLatestBoardCommentsByUserDtos) {
        super(HttpStatus.OK.value(), "User Main");
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.cityName = user.getCity().getName();
        this.zoneName = user.getZone().getName();
        this.point = user.getPoint();
        this.profileImg = user.getProfileImg();
        this.findLatestBoardsByUserDtos = findLatestBoardsByUserDtos;
        this.findLatestBoardCommentsByUserDtos = findLatestBoardCommentsByUserDtos;
    }
}
