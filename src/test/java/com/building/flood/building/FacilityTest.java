package com.building.flood.building;

import com.building.flood.building.domain.Building;
import com.building.flood.building.domain.FloodFacility;
import com.building.flood.building.domain.FloodFacilityRepo;
import com.building.flood.building.dto.ViewBuildingRes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
public class FacilityTest {

    @Autowired
    private FloodFacilityRepo floodFacilityRepo;

    @Test
    @DisplayName("주소로 시설 찾기")
    public ViewBuildingRes.BuildingData.Inspection findFacility(){
        String address = "주소";
        Building building = Building.builder().landLocation("주소").build();
        FloodFacility floodFacility = FloodFacility.builder()
                .address("주소")
                .facility("차수벽")
                .build();
        floodFacilityRepo.save(floodFacility);
        List<FloodFacility> floodFacilityList = floodFacilityRepo.findByAddress(building.getLandLocation());
        List<String> facilities = floodFacilityList.stream().map(
                f-> f.getFacility()
        ).collect(Collectors.toList());

        if(floodFacilityList.size()==0) return ViewBuildingRes.BuildingData.Inspection.builder()
                .facilities(Collections.EMPTY_LIST)
                .yn("n")
                .build();

        return ViewBuildingRes.BuildingData.Inspection.builder()
                .yn("y")
                .facilities(facilities).build();
//        Assertions.assertEquals(0, floodFacilityList.size());

    }


}
