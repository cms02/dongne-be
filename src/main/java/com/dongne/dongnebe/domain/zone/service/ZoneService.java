package com.dongne.dongnebe.domain.zone.service;


import com.dongne.dongnebe.domain.zone.dto.ZoneCodeNameDto;
import com.dongne.dongnebe.domain.zone.dto.response.ZoneResponseDto;
import com.dongne.dongnebe.domain.zone.repository.ZoneQueryRepository;
import com.dongne.dongnebe.domain.zone.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZoneService {
    private final ZoneRepository zoneRepository;
    private final ZoneQueryRepository zoneQueryRepository;
    public ZoneResponseDto findAllZoneByCityCodeOrderByCityCodeAsc(String cityCode) {
        List<ZoneCodeNameDto> zoneCodeNameDtos = zoneQueryRepository.findAllByCityCodeOrderByZoneCodeAsc(cityCode).stream()
                .map(z -> ZoneCodeNameDto.builder()
                        .zoneCode(z.getZoneCode())
                        .name(z.getName())
                        .build())
                .collect(Collectors.toList());

        return ZoneResponseDto.builder()
                .zoneCodeNames(zoneCodeNameDtos)
                .responseMessage("Find Zone Codes And Names")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
