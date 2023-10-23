package com.dongne.dongnebe.domain.category.channel.dto.response;

import com.dongne.dongnebe.domain.category.channel.dto.ChannelDto;
import com.dongne.dongnebe.domain.category.channel.dto.FindHotChannelsDto;
import com.dongne.dongnebe.domain.category.sub_category.dto.SubCategoryDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@SuperBuilder
public class ChannelResponseDto extends ResponseDto {
    private List<ChannelDto> channelDtos;

    public ChannelResponseDto(List<ChannelDto> channelDtos) {
        super(HttpStatus.OK.value(), "Find All Channels");
        this.channelDtos = channelDtos;
    }
}
