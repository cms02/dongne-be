package com.dongne.dongnebe.domain.city.entity;

import com.dongne.dongnebe.domain.board.entity.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "city")
    private List<Board> boardList = new ArrayList<>();
}
