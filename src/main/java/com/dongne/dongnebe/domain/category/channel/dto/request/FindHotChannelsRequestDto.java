package com.dongne.dongnebe.domain.category.channel.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindHotChannelsRequestDto {
    private String cityCode;
    private String zoneCode;
    private Long subCategoryId;
    private Long dataCount;
}
