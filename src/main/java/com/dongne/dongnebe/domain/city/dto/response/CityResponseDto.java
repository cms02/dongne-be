package com.dongne.dongnebe.domain.city.dto.response;

import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class CityResponseDto extends ResponseDto {
    private List<CityCodeNameDto> cityCodeNames;
}
