package com.dongne.dongnebe.domain.city.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "city")
@Getter
@NoArgsConstructor
public class City {

    @Id
    @Column(name = "city_code")
    private String cityCode;
    private String name;

    @Builder
    public City(String cityCode, String name) {
        this.cityCode = cityCode;
        this.name = name;
    }
}
