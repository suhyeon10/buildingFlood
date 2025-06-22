package com.building.flood.building.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionRepo extends JpaRepository<Region, Long> {

    Optional<Region> findFirstByCityDistrictCodeAndLegalDongCode(String cityDistrictCode, String legalDongCode);

}
