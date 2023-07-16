package com.dongne.dongnebe.domain.zone.controller;

import com.dongne.dongnebe.domain.zone.dto.response.ZoneResponseDto;
import com.dongne.dongnebe.domain.zone.service.ZoneService;
import com.dongne.dongnebe.global.dto.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService zoneService;
    @GetMapping("/api/zone/{cityCode}")
    public ResponseEntity<ResponseDto> findAllZoneByCityCode(@PathVariable String cityCode) {
        ZoneResponseDto result = zoneService.findAllZoneByCityCodeOrderByCityCodeAsc(cityCode);
        return ResponseEntity.ok().body(result);
}
}


