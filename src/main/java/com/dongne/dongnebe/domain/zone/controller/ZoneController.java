package com.dongne.dongnebe.domain.zone.controller;

import com.dongne.dongnebe.domain.zone.dto.ZoneResponseDto;
import com.dongne.dongnebe.domain.zone.service.ZoneService;
import com.dongne.dongnebe.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ZoneController {
    private final ZoneService zoneService;
    @GetMapping("/api/zones/{cityCode}")
    public ResponseEntity<ResponseDto> findAllZonesByCityCode(@PathVariable String cityCode) {
        ZoneResponseDto result = zoneService.findAllZonesByCityCodeOrderByCityCodeAsc(cityCode);
        return ResponseEntity.ok().body(result);
}
}


