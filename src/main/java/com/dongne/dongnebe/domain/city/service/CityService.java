package com.dongne.dongnebe.domain.city.service;


import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    @Transactional(readOnly = true)
    public CityResponseDto findAllCityOrderByCityCodeAsc() {
        List<CityCodeNameDto> cityCodeNameDtos = cityRepository.findAllByOrderByCityCodeAsc().stream()
                .map(c -> CityCodeNameDto.builder()
                        .cityCode(c.getCityCode())
                        .name(c.getName())
                        .build())
                .collect(Collectors.toList());

        return CityResponseDto.builder()
                .cityCodeNames(cityCodeNameDtos)
                .responseMessage("Find City Codes And Names")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
