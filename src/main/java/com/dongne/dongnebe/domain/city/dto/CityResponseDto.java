package com.dongne.dongnebe.domain.city.dto;

import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class CityResponseDto extends ResponseDto {
    private List<CityCodeNameDto> cityCodeNames;
}
