package com.dongne.dongnebe.domain.city.dto;

import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@Builder
public class CityCodeNameDto {
    private String cityCode;
    private String name;
}
