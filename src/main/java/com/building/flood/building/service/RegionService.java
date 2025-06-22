package com.building.flood.building.service;

import com.building.flood.building.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepo regionRepo;
    private final RegionNotificationRepo regionNotificationRepo;

    public RegionNotification retrieveRegionNotification(String cityDistrictCode, String legalDongCode) {
        Region region = findRegionByRegionCode(cityDistrictCode, legalDongCode);

        if (region == null) {
            log.info("조회된 리전이 없습니다.");
            return RegionNotification.builder().build();
        }

        return regionNotificationRepo.findFirstByRegionOrderByCreateAtDesc(region)
                .orElseGet(() -> {
                    log.info("조회된 공지가 없습니다.");
                    return RegionNotification.builder().build();
                });
    }

    private Region findRegionByRegionCode(String cityDistrictCode, String legalDongCode ) {
        return regionRepo.findFirstByCityDistrictCodeAndLegalDongCode(cityDistrictCode, legalDongCode)
                .orElse(null);
    }
}
