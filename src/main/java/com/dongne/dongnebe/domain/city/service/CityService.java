package com.dongne.dongnebe.domain.city.service;


import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    public CityResponseDto findAllCityOrderByCityCodeAsc() {
        List<CityCodeNameDto> CityCodeNameDtos = cityRepository.findAllByOrderByCityCodeAsc().stream()
                .map(c -> CityCodeNameDto.builder()
                        .cityCode(c.getCityCode())
                        .name(c.getName())
                        .build())
                .collect(Collectors.toList());

        return CityResponseDto.builder()
                .cityCodeNames(CityCodeNameDtos)
                .responseMessage("Find City Codes And Names")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
