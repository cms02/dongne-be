package com.dongne.dongnebe.domain.city.service;


import com.dongne.dongnebe.domain.city.dto.CityCodeNameDto;
import com.dongne.dongnebe.domain.city.dto.CityNameCountDto;
import com.dongne.dongnebe.domain.city.dto.response.CityResponseDto;
import com.dongne.dongnebe.domain.city.dto.response.TopNCityResponseDto;
import com.dongne.dongnebe.domain.city.repository.CityQueryRepository;
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
    private final CityQueryRepository cityQueryRepository;
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

    @Transactional(readOnly = true)
    public TopNCityResponseDto findBoardsTopNCity(Long cityCount) {
        List<CityNameCountDto> cityNameCountDtos =  cityQueryRepository.findBoardsTopNCity(cityCount);
        return new TopNCityResponseDto(cityNameCountDtos);
    }
}
