package com.dongne.dongnebe.domain.category.channel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDto {
    private Long channelId;
    private String name;
    private Long boardCount;
}
