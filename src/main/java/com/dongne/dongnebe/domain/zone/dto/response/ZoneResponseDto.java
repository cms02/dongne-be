package com.dongne.dongnebe.domain.zone.dto.response;

import com.dongne.dongnebe.domain.zone.dto.ZoneCodeNameDto;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class ZoneResponseDto extends ResponseDto {
    private List<ZoneCodeNameDto> zoneCodeNames;
}
