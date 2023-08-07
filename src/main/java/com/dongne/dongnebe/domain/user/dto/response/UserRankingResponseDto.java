package com.dongne.dongnebe.domain.user.dto.response;

import com.dongne.dongnebe.domain.user.dto.FindLatestBoardCommentsByUserDto;
import com.dongne.dongnebe.domain.user.dto.FindLatestBoardsByUserDto;
import com.dongne.dongnebe.domain.user.dto.UserRankingDto;
import com.dongne.dongnebe.domain.user.entity.User;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class UserRankingResponseDto extends ResponseDto {
    List<UserRankingDto> userRankingDtos;
    public UserRankingResponseDto(List<UserRankingDto> userRankingDtos) {
        super(HttpStatus.OK.value(), "User Ranking");
        this.userRankingDtos = userRankingDtos;
    }
}
