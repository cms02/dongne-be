package com.dongne.dongnebe.domain.category.channel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindHotChannelsDto {
    private Long channelId;
    private String channelName;
}
