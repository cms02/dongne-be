package com.dongne.dongnebe.domain.zone.repository;

import com.dongne.dongnebe.domain.zone.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, String> {
}
