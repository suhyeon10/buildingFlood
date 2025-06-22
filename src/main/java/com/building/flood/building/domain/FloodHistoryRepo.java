package com.building.flood.building.domain;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FloodHistoryRepo extends JpaRepository<FloodHistory, Long> {
    Optional<FloodHistory> findFirstByAddressOrderByFloodYearDesc(String address);
    List<FloodHistory> findByAddressOrderByFloodYearDesc(String address);


}
