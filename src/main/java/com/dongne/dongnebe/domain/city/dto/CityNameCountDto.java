package com.dongne.dongnebe.domain.city.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityNameCountDto {
    private String name;
    private Long boardCount;
}
