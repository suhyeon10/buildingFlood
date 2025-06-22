package com.building.flood.building.service;

import com.building.flood.building.domain.*;
import com.building.flood.building.dto.ViewBuildingRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MapViewService {

    private final BuildingRepo buildingRepo;
    private final FloodFacilityRepo floodFacilityRepo;
    private final FloodHistoryRepo floodHistoryRepo;
    private final SafetyScoreRepo safetyScoreRepo;
    private final RegionService regionService;

    @Transactional
    public ViewBuildingRes viewMapData(double lat_n ,
                            double lat_s ,
                            double lng_n ,
                            double lng_s ,
                            String cityDistrictCode,
                            String legalDongCode){

        List<Building> buildingList = buildingRepo.findBuildingsByCityDistrictCodeAndLegalDongCodeAndLatitudeBetweenAndLongitudeBetween(cityDistrictCode, legalDongCode, lat_n, lat_s, lng_n, lng_s);
        RegionNotification regionNotification = regionService.retrieveRegionNotification(cityDistrictCode, legalDongCode);

        if(buildingList.size()<=0) {
            log.info("조회된 건물이 없습니다.");
            return ViewBuildingRes.of(regionNotification);
        }
        return createViewBuildingRes(buildingList,regionNotification);
    }


    private ViewBuildingRes createViewBuildingRes(List<Building> buildings, RegionNotification regionNotification) {
        List<ViewBuildingRes.BuildingData> buildingDataList = buildings.stream()
                .map(this::createBuildingData)
                .collect(Collectors.toList());

        return ViewBuildingRes.of(buildingDataList,regionNotification);
    }

    private ViewBuildingRes.BuildingData createBuildingData(Building building) {
        try {
            SafetyScore safetyScore = retrieveOrCreateSafetyScore(building);
            List<FloodHistory> floodHistories = retrieveFloodHistories(building);
            List<FloodFacility> floodFacilities = retrieveInspection(building);


            return ViewBuildingRes.BuildingData.of(building, floodHistories, safetyScore, floodFacilities);
        } catch (ParseException e) {
            throw new IllegalStateException("건물 데이터 생성에 실패했습니다: " + building, e);
        }
    }



    private List<FloodHistory> retrieveFloodHistories(Building building) {
        // 건물의 홍수 이력 조회
        return floodHistoryRepo.findByAddressOrderByFloodYearDesc(building.getLandLocation());
    }

    private SafetyScore retrieveOrCreateSafetyScore(Building building) {
        // 건물의 안전 점수 조회 또는 안전 점수가 없다면 생성
        return safetyScoreRepo.findFirstByBuilding(building).orElseGet(() -> {
            int score = calculateSafetyScore(building);
            SafetyScore newSafetyScore = SafetyScore.from(score, building);
            safetyScoreRepo.save(newSafetyScore);
            return newSafetyScore;
        });
    }

//    private FloodFacility retrieveInspection(Building building) {
//        // 건물의 점검 이력 조회
//        return floodFacilityRepo.findFirstByBuilding(building).orElse(FloodFacility.builder().build());
//    }

    private List<FloodFacility> retrieveInspection(Building building) {
        // 건물의 점검 이력 조회
        return floodFacilityRepo.findByAddress(building.getLandLocation());
    }

    private int calculateSafetyScore(Building building){
        // 안전 점수 계산
        int score = 0;
        score += calculateFloodScore(building);
        score += calculateBuildingGrade(building.getUsageApprovalDate());
        score += calculateBelowFloorScore(building.getBelowGroundFloorCount());
        return score;
    }

    private int calculateFloodScore(Building building){
        FloodHistory latestFloodHistory = getLatestFloodHistory(building);
        if (latestFloodHistory == null) {
            return 1;
        }
        int currentYear = LocalDate.now().getYear();
        int floodYear = latestFloodHistory.getFloodYear();
        // 침수 기록이 5년 이내면 4점, 아니면 2점, 없으면 1점
        return (currentYear - floodYear <= 5) ? 4 : 2;
    }

    private FloodHistory getLatestFloodHistory(Building building) {
        // 최근 홍수 이력 조회
        return floodHistoryRepo.findFirstByAddressOrderByFloodYearDesc(building.getLandLocation()).orElse(null);
    }

    private int calculateBelowFloorScore(int belowGroundFloorCount){
        // 지하 층수 점수 계산
        return (belowGroundFloorCount > 0) ? 3 : 1;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    // 건물 등급 계산
    private static int calculateBuildingGrade(String usageApprovalDate) {
//        int approvalYear = LocalDate.parse(usageApprovalDate, DATE_FORMATTER).getYear();

        if (usageApprovalDate == null || usageApprovalDate.length() < 4) {
            // 오류 처리 또는 기본값 반환
            return 0; // 또는 다른 값을 반환하거나 예외를 던질 수 있음
        }

        // 연도 부분만 추출
        int approvalYear = Integer.parseInt(usageApprovalDate.substring(0, 4));

        if (approvalYear >= 1970 && approvalYear <= 1989) {
            return 3;
        } else if (approvalYear >= 1990 && approvalYear <= 2009) {
            return 2;
        } else if (approvalYear >= 2010 && approvalYear <= 2029) {
            return 1;
        } else {
            return 0;
        }
    }
}
