package com.building.flood.building.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "building")
public class Building {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "building_id")
//    private Long id;

    @Id
    @Column(name = "management_building_registry_PK")  // 관리_건축물대장_PK
    private String managementBuildingRegistryPK;

    @Column(name = "usage_approval_date")  // 사용승인_일
    private String usageApprovalDate;

    private Double longitude;
    private Double latitude;

    @Column(name = "land_location")  // 대지_위치
    private String landLocation;

    @Column(name = "road_land_location")  // 도로명_대지_위치
    private String roadLandLocation;

    @Column(name = "building_name")  // 건물_명
    private String buildingName;

    @Column(name = "city_district_code")  // 시군구_코드
    private String cityDistrictCode;

    @Column(name = "legal_dong_code")  // 법정동_코드
    private String legalDongCode;

    @Column(name = "number")  // 번
    private String number;

    @Column(name = "ji")  // 지
    private String ji;
    @Column(name = "main_use_code_name")  // 주_용도_코드_명
    private String mainUseCodeName;

    @Column(name = "above_ground_floor_count")  // 지상_층_수
    private Integer aboveGroundFloorCount;

    @Column(name = "below_ground_floor_count")  // 지하_층_수
    private Integer belowGroundFloorCount;


//    @Column(name = "land_type_code")  // 대지_구분_코드
//    private String landTypeCode;



//    @Column(name = "special_land_name")  // 특수지_명
//    private String specialLandName;

//    @Column(name = "block")  // 블록
//    private String block;
//
//    @Column(name = "lot")  // 로트
//    private String lot;
//
//    @Column(name = "special_land_count")  // 외필지_수
//    private Integer specialLandCount;
//
//    @Column(name = "dong_name")  // 동_명
//    private String dongName;

//    @Column(name = "main_sub_structure_code")  // 주_부속_구분_코드
//    private String mainSubStructureCode;
//
//    @Column(name = "main_sub_structure_code_name")  // 주_부속_구분_코드_명
//    private String mainSubStructureCodeName;
//
//    @Column(name = "land_area_square_meters")  // 대지_면적(㎡)
//    private Double landAreaSquareMeters;
//
//    @Column(name = "building_area_square_meters")  // 건축_면적(㎡)
//    private Double buildingAreaSquareMeters;
//
//    @Column(name = "building_coverage_ratio_percent")  // 건폐_율(%)
//    private Double buildingCoverageRatioPercent;
//
//    @Column(name = "floor_area_square_meters")  // 연면적(㎡)
//    private Double floorAreaSquareMeters;



//    @Column(name = "structure_code_name")  // 구조_코드_명
//    private String structureCodeName;

//    @Column(name = "other_structure")  // 기타_구조
//    private String otherStructure;
//
//    @Column(name = "main_use_code")  // 주_용도_코드
//    private String mainUseCode;



//    @Column(name = "other_use")  // 기타_용도
//    private String otherUse;
//
//    @Column(name = "roof_code")  // 지붕_코드
//    private String roofCode;
//
//    @Column(name = "roof_code_name")  // 지붕_코드_명
//    private String roofCodeName;
//
//    @Column(name = "other_roof")  // 기타_지붕
//    private String otherRoof;

//    @Column(name = "household_count")  // 세대_수(세대)
//    private Integer householdCount;

//    @Column(name = "family_count")  // 가구_수(가구)
//    private Integer familyCount;

//    @Column(name = "height_meters")  // 높이(m)
//    private Double heightMeters;



//    @Column(name = "elevator_count")  // 승용_승강기_수
//    private Integer elevatorCount;
//
//    @Column(name = "emergency_elevator_count")  // 비상용_승강기_수
//    private Integer emergencyElevatorCount;
//
//    @Column(name = "attached_building_count")  // 부속_건축물_수
//    private Integer attachedBuildingCount;
//
//    @Column(name = "attached_building_area_square_meters")  // 부속_건축물_면적(㎡)
//    private Double attachedBuildingAreaSquareMeters;
//
//    @Column(name = "total_building_floor_area_square_meters")  // 총_동_연면적(㎡)
//    private Double totalBuildingFloorAreaSquareMeters;
//
//    @Column(name = "indoor_mechanical_number")  // 옥내_기계식_대수(대)
//    private Integer indoorMechanicalNumber;
//
//    @Column(name = "indoor_mechanical_area_square_meters")  // 옥내_기계식_면적(㎡)
//    private Double indoorMechanicalAreaSquareMeters;
//
//    @Column(name = "outdoor_mechanical_number")  // 옥외_기계식_대수(대)
//    private Integer outdoorMechanicalNumber;
//
//    @Column(name = "outdoor_mechanical_area_square_meters")  // 옥외_기계식_면적(㎡)
//    private Double outdoorMechanicalAreaSquareMeters;
//
//    @Column(name = "indoor_self_powered_number")  // 옥내_자주식_대수(대)
//    private Integer indoorSelfPoweredNumber;
//
//    @Column(name = "indoor_self_powered_area_square_meters")  // 옥내_자주식_면적(㎡)
//    private Double indoorSelfPoweredAreaSquareMeters;
//
//    @Column(name = "outdoor_self_powered_number")  // 옥외_자주식_대수(대)
//    private Integer outdoorSelfPoweredNumber;
//
//    @Column(name = "outdoor_self_powered_area_square_meters")  // 옥외_자주식_면적(㎡)
//    private Double outdoorSelfPoweredAreaSquareMeters;
}
