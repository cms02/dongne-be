package com.dongne.dongnebe.domain.city.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CityCodeNameDto {
    private String cityCode;
    private String name;
}
