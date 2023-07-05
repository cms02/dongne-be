package com.dongne.dongnebe.domain.city.repository;

import com.dongne.dongnebe.domain.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, String> {

    List<City> findAllByOrderByCityCodeAsc();
}
