package com.dongne.dongnebe.domain.zone.entity;

import com.dongne.dongnebe.domain.city.entity.City;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zone")
@Getter
@NoArgsConstructor
public class Zone {

    @Id
    @Column(name = "zone_code")
    private String zoneCode;
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_code")
    private City city;

    @Builder
    public Zone(String zoneCode, String name, City city) {
        this.zoneCode = zoneCode;
        this.name = name;
        this.city = city;
    }
}
