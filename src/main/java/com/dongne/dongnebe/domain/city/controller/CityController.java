package com.dongne.dongnebe.domain.city.controller;

import com.dongne.dongnebe.domain.board.dto.response.FindSearchBoardsResponseDto;
import com.dongne.dongnebe.domain.city.dto.response.CityResponseDto;
import com.dongne.dongnebe.domain.city.dto.response.TopNCityResponseDto;
import com.dongne.dongnebe.domain.city.service.CityService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    @GetMapping("/api/city")
    public ResponseEntity<ResponseDto> findAllCity() {
        CityResponseDto result = cityService.findAllCityOrderByCityCodeAsc();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/city/top/{cityCount}")
    public ResponseEntity<ResponseDto> findBoardsTopNCity(@PathVariable("cityCount") Long cityCount) {
        TopNCityResponseDto result = cityService.findBoardsTopNCity(cityCount);
        return ResponseEntity.ok().body(result);
    }
}


