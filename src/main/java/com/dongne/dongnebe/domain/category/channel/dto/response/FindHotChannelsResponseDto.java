package com.dongne.dongnebe.domain.category.channel.dto.response;

import com.dongne.dongnebe.domain.board.dto.FindHotBoardsByCategoriesDto;
import com.dongne.dongnebe.domain.category.channel.dto.FindHotChannelsDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class FindHotChannelsResponseDto extends ResponseDto {
    List<FindHotChannelsDto> findHotChannelsDtos;

    public FindHotChannelsResponseDto(List<FindHotChannelsDto> findHotChannelsDtos) {
        super(HttpStatus.OK.value(), "Find Hot Channels");
        this.findHotChannelsDtos = findHotChannelsDtos;
    }
}
