package com.building.flood.building.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SafetyScoreRepo extends JpaRepository<SafetyScore, Long> {
    Optional<SafetyScore> findFirstByBuilding(Building building);
}
