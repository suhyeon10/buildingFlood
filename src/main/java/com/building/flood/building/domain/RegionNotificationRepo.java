package com.building.flood.building.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegionNotificationRepo extends JpaRepository<RegionNotification, Long> {

    Optional<RegionNotification> findFirstByRegionOrderByCreateAtDesc(Region region);
}
