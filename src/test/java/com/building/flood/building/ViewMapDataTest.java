//package com.building.flood.building;
//
//import com.building.flood.building.domain.*;
//import com.building.flood.building.dto.ViewBuildingRes;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import javax.swing.text.View;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//public class ViewMapDataTest {
//
//    @Autowired
//    private BuildingRepo buildingRepo;
//    @Autowired
//    private FloodHistoryRepo floodHistoryRepo;
//    @Autowired
//    private SafetyScoreRepo safetyScoreRepo;
//    @Autowired
//    private FloodFacilityRepo floodFacilityRepo;
//
//    @Test
//    @DisplayName("위도,경도 바운더리에 따라 빌딩 데이터 조회")
//    @Transactional
//    public void viewMapData(){
//        double lat_n = 30;
//        double lat_s = 40;
//        double lng_n = 130;
//        double lng_s = 140;
//
//        //시군구, 법정동 코드
//        String cityDistrictCode ="11650";
//        String legalDongCode = "10800";
//
//        //위도와 경도 바운더리가 오면, 그에 따른 전체 데이터 리턴
//
//        Building building = Building.builder()
//                .managementBuildingRegistryPK("122")
//                .latitude(40.0).longitude(135.0)
//                .cityDistrictCode("11650")
//                .legalDongCode("10800")
//                .usageApprovalDate("20220101")
//                .aboveGroundFloorCount(5)
//                .belowGroundFloorCount(2)
//                .build();
//
//        Building building1 = Building.builder()
//                .managementBuildingRegistryPK("123") // 다른 값으로 설정
//                .latitude(40.0).longitude(135.0)
//                .cityDistrictCode("11650")
//                .legalDongCode("10800")
//                .usageApprovalDate("20220101")
//                .belowGroundFloorCount(2)
//                .build();
//
//        buildingRepo.save(building);
//        buildingRepo.save(building1);
//
////        List<Building> buildingList = buildingRepo.findBuildingsByLatitudeBetweenAndLongitudeBetween(lat_n, lat_s, lng_n, lng_s);
//        List<Building> buildingList = buildingRepo.findBuildingsByCityDistrictCodeAndLegalDongCodeAndLatitudeBetweenAndLongitudeBetween(cityDistrictCode, legalDongCode, lat_n, lat_s, lng_n, lng_s);
//
//
////        if(buildingList.size()<0) //null처리
//
////        assertEquals(buildingList.size(), 0);
////        assertEquals(buildingList.get(0).getSafetyScore().getScore(), 10);
//
//        ViewBuildingRes viewBuildingRes = makeViewBuildingRes(buildingList);
//        assertEquals(viewBuildingRes.getBuildings().get(0).getSafety().getScore(), 5);
//        assertNotNull(viewBuildingRes);
//    }
//
//
//
//    public ViewBuildingRes makeViewBuildingRes(List<Building> buildings){
//        List<ViewBuildingRes.BuildingData> buildingData = buildings.stream().map(b -> {
//            try {
//                return makeBuildingRes(b);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//        }).collect(Collectors.toList());
//
//        return ViewBuildingRes.builder().buildings(buildingData).build();
//
//    }
//
//    private static <T> T defaultIfNull(T value, T defaultValue) {
//        return (value != null) ? value : defaultValue;
//    }
//
//    @Autowired
//    private RegionRepo regionRepo;
//
//    @Autowired
//    private RegionNotificationRepo regionNotificationRepo;
//    @Test
//    @DisplayName("공지 잘가져와지는지 테스트")
//    public void retrieveRegionNotification(){
//
//
//        //시군구, 법정동 코드
//        String cityDistrictCode ="11650";
//        String legalDongCode = "10800";
//
//        Region newRegion = Region.builder()
//                .cityDistrictCode("11650")
//                .legalDongCode("10800").build();
//        regionRepo.save(newRegion);
//
//        RegionNotification newRegionNoti = RegionNotification.builder()
//                .region(newRegion)
//                .content("뉴")
//                .createAt(LocalDateTime.now())
//                .build();
////        regionNotificationRepo.save(newRegionNoti);
//        Region region = regionRepo.findFirstByCityDistrictCodeAndLegalDongCode(cityDistrictCode, legalDongCode)
//                .orElse(null);
//
//        Assertions.assertNotNull(region);
//        if(region==null) {
//            return;
////            return RegionNotification.builder().build();
//        }
//
//        RegionNotification regionNotification = regionNotificationRepo.findFirstByRegionOrderByCreateAtDesc(region)
//                .orElseGet(() ->{
//                    return RegionNotification.builder().build();
//                });
//
//        Assertions.assertNotNull(regionNotification);
////        Assertions.assertEquals("뉴", regionNotification.getContent());
//
//    }
//
//    @Test
//    @DisplayName("공지 -> dto")
//    public void of(){
//        RegionNotification regionNotification = RegionNotification.builder().build();
//
//
//        ViewBuildingRes viewBuildingRes = ViewBuildingRes.builder()
//                .notification(defaultIfNull(regionNotification.getContent(), ""))
//                .build();
//
//        assertEquals("", viewBuildingRes.getNotification());
//
//    }
//
//
//    @Test
//    @DisplayName("조회 API 응답값 만들기")
//    public ViewBuildingRes.BuildingData makeBuildingRes(Building building) throws ParseException {
//        SafetyScore safetyScore = viewSafetyScore(building);
//        List<FloodHistory> floodHistorys = viewFloodHistory(building);
//        FloodFacility floodFacility = viewIspection(building);
//        ViewBuildingRes.BuildingData buildingData = ViewBuildingRes.BuildingData.of(building, floodHistorys, safetyScore, floodFacility);
//        return buildingData;
//    }
//
//
//    @Test
//    @DisplayName("빌딩의 침수여부 확인")
//    public List<FloodHistory> viewFloodHistory(Building building){
//        List<FloodHistory> floodHistorys = floodHistoryRepo.findByBuildingOrderByFloodYearDesc(building);
//
//        assertEquals(floodHistorys.size(), 0);
//        return floodHistorys;
//
//
//    }
//
//    @Test
//    @DisplayName("빌딩의 안전점수가 없으면 추가하고 조회")
//    public SafetyScore viewSafetyScore(Building building){
//
//
//        SafetyScore safetyScore = safetyScoreRepo.findFirstByBuilding(building).orElse(null);
//        int score = calSafetyScore(building);
//
//
//        assertNull(safetyScore);
//        if(safetyScore!=null) return safetyScore;
//
//
//        SafetyScore newSafetyScore = SafetyScore.builder().score(score)
//                .status(SafetyStatus.getSafetyStatus(score))
//                .building(building)
//                .build();
//
//        assertEquals(SafetyStatus.FLOOD_CAUTION, newSafetyScore.getStatus());
//        safetyScoreRepo.save(newSafetyScore);
//
//        return newSafetyScore;
//    }
//    @Test
//    @DisplayName("안전점수가 달라졌다면 업데이트")
//    public void chkSafetyScore(){
//        int score = 0;
//        Building building = Building.builder()
//                .managementBuildingRegistryPK("123")
//                .latitude(40.0).longitude(135.0)
//                .cityDistrictCode("11650")
//                .legalDongCode("10800")
//                .managementBuildingRegistryPK("0")
//                .usageApprovalDate("20220101")
//                .aboveGroundFloorCount(5)
//                .belowGroundFloorCount(2)
//                .build();
//        SafetyScore safetyScore = safetyScoreRepo.findFirstByBuilding(building).orElse(null);
//
//        if(score != safetyScore.getScore()){
////            safetyScore.
//        }
//    }
//
//    @Test
//    @DisplayName("점검 내용 데이터 조회")
//    public FloodFacility viewIspection(Building building){
//        FloodFacility floodFacility = floodFacilityRepo.findFirstByBuilding(building).orElse(null);
//        assertNull(floodFacility);
//        if(floodFacility==null) return FloodFacility.builder().build();
//        return floodFacility;
//    }
//
//    @Test
//    @DisplayName("빌딩의 안전 점수 구하기")
//    public int calSafetyScore(Building building){
//
//        int score = 0;
//
//
//
//        //침수시기가 5년 이내 -> 4
//        //침수시기가 5년 이전 -> 2
//        //침수X -> 1
//
//        score += calculateFloodYear(building);
//
//        assertEquals(1, score);
//
//        //사용승인일이 7~80년대 -> 3
//        //사용승인일이 9~00년대 -> 2
//        //사용승인일이 1~20년대 -> 1
//
//        score += calculateBuildingGrade(building.getUsageApprovalDate());
//
//        assertEquals(score, 2);
//
//        //지하층수가 있으면 -> 3
//        //지하층수가 없으면 -> 1
//
//        score += calculateBelowFloor(building.getBelowGroundFloorCount());
//
//        assertEquals(score, 5);
//
//        return score;
//    }
//
//    private int calculateFloodYear(Building building){
//        FloodHistory floodHistory = floodHistoryRepo.findFirstByBuildingOrderByFloodYearDesc(building).orElse(null);
//
//        int currentYear = LocalDate.now().getYear();
//        if (floodHistory == null) return 1;
//        int floodYear = floodHistory.getFloodYear();
//        if (currentYear - floodYear <= 5) {
//            return 4;
//        }
//        else {
//            return 2;
//        }
//
//    }
//    private int calculateBelowFloor(int belowGroundFloorCount){
//        if(belowGroundFloorCount>0) return 3;
//        else return 1;
//    }
//
//    private static int calculateBuildingGrade(String usageApprovalDate) {
//        int approvalYear = LocalDate.parse(usageApprovalDate,
//                DateTimeFormatter.ofPattern("yyyyMMdd")).getYear();
//
//        if (approvalYear >= 1970 && approvalYear <= 1989) {
//            return 3;
//        } else if (approvalYear >= 1990 && approvalYear <= 2009) {
//            return 2;
//        } else if (approvalYear >= 2010 && approvalYear <= 2029) {
//            return 1;
//        } else {
//            throw new IllegalArgumentException("Invalid usageApprovalDate year: " + approvalYear);
//        }
//    }
//}
