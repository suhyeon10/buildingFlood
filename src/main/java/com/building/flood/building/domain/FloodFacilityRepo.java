package com.building.flood.building.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FloodFacilityRepo extends JpaRepository<FloodFacility, Long> {
    List<FloodFacility> findByAddress(String address);
}
