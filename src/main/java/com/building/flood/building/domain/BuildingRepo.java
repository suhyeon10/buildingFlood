package com.building.flood.building.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepo extends JpaRepository<Building, String> {

    List<Building> findBuildingsByCityDistrictCodeAndLegalDongCodeAndLatitudeBetweenAndLongitudeBetween(
            String cityDistrictCode, String legalDongCode,
            double lat_n, double lat_s, double lng_n, double lng_s);

}
