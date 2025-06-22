package com.building.flood.building;

import com.building.flood.building.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class FilterFloodBuildingTest {

    @Autowired
    private SafetyScoreRepo safetyScoreRepo;
    @Autowired
    private FloodFacilityRepo floodFacilityRepo;
    @Autowired
    private BuildingRepo buildingRepo;
    @Autowired
    private FloodHistoryRepo floodHistoryRepo;

//    @Test
//    @DisplayName("침수된 여부 확인하기")
//    public void filterFloodBuilding(){
//
//        String pk = "123";
//        Building building = Building.builder().managementBuildingRegistryPK(pk).build();
//        // 해당 빌딩의 침수 기록 조회
////        List<FloodHistory> floodHistories = floodHistoryRepo.findByBuilding OrderByFloodYearDesc(building);
//
//        // 침수 기록이 있는지 확인
//        if (!floodHistories.isEmpty()) {
//            // 침수 기록이 있으면 처리할 로직 추가
//            System.out.println("빌딩이 침수되었습니다.");
//        } else {
//            // 침수 기록이 없으면 처리할 로직 추가
//            System.out.println("빌딩이 침수되지 않았습니다.");
//        }
//    }
    @Test
    @DisplayName("미조치된 빌딩 확인하기")
    public void filterNoActionBuilding(){

    }
    @Test
    @DisplayName("경고된 빌딩 확인하기")
    public void filterWarningBuilding(){


    }
}
