package com.dongne.dongnebe.domain.city.controller;

import com.dongne.dongnebe.domain.city.dto.CityResponseDto;
import com.dongne.dongnebe.domain.city.service.CityService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    @GetMapping("/api/city")
    public ResponseEntity<ResponseDto> findAllCity() {
        CityResponseDto result = cityService.findAllCityOrderByCityCodeAsc();
        return ResponseEntity.ok().body(result);
}}


