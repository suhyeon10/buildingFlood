package com.building.flood.building;

import com.building.flood.building.domain.*;
import com.building.flood.building.dto.ViewBuildingRes;
import com.building.flood.common.exception.BusinessException;
import com.building.flood.common.exception.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class RegionNotiTest {

    @Autowired
    private RegionRepo regionRepo;

    @Autowired
    private RegionNotificationRepo regionNotificationRepo;
    @Test
    @DisplayName("공지 잘가져와지는지 테스트")
    public void testGetRegionNoti(){
        //시군구, 법정동 코드
        String cityDistrictCode ="11650";
        String legalDongCode = "10800";

        RegionNotification regionNotification = retrieveRegionNotification(Building.builder().build());
        of(regionNotification);
    }


    @Test
    public RegionNotification retrieveRegionNotification(Building building) {
        Region region = findRegionByBuilding(building);

        if (region == null) {
            return RegionNotification.builder().build();
        }

        return regionNotificationRepo.findFirstByRegionOrderByCreateAtDesc(region)
                .orElse(RegionNotification.builder().build());
    }

    private Region findRegionByBuilding(Building building) {
        return regionRepo.findFirstByCityDistrictCodeAndLegalDongCode(building.getCityDistrictCode(), building.getLegalDongCode()).orElse(null);
    }

    private static <T> T defaultIfNull(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }
    @Test
    @DisplayName("공지 -> dto")
    public ViewBuildingRes of(RegionNotification regionNotification){
        ViewBuildingRes viewBuildingRes = ViewBuildingRes.builder()
                .notification(defaultIfNull(regionNotification.getContent(), ""))
                .build();

        assertEquals("", viewBuildingRes.getNotification());

        return viewBuildingRes;
    }

}
