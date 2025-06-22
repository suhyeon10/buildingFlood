package com.building.flood.building.dto;

import com.building.flood.building.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class ViewBuildingRes {

    private String notification;
    private List<BuildingData> buildings;
    @Getter
    @Builder
    public static class BuildingData{
        private String pk;
        private Double longitude;
        private Double latitude;
        private String buildingName;
        private String address;
        private String year;
        private String usage;
        private int belowFloorNum;
        private int aboveFloorNum;
        private Safety safety;
        private List<Integer> floodHistoryYears;
        private Inspection inspection;

        @Getter
        @Builder
        public static class Safety{
            private String status;
            private int score;

            public static Safety of(SafetyScore safetyScore){
                return ViewBuildingRes.BuildingData.Safety.builder()
                        .score(defaultIfNull(safetyScore.getScore(),0))
                        .status(defaultIfNull(safetyScore.getStatus().toString(), ""))
                        .build();
            }

            public static ViewBuildingRes.BuildingData.Safety of() {
                return ViewBuildingRes.BuildingData.Safety.builder()
                        .status("")
                        .score(0)
                        .build();
            }
        }

        @Getter
        @Builder
        public static class Inspection{
            private String yn;
            private List<String> facilities;

            public static Inspection of(List<FloodFacility> floodFacilityList) {
                if (floodFacilityList.isEmpty()) {
                    return ViewBuildingRes.BuildingData.Inspection.builder()
                            .facilities(Collections.emptyList())
                            .yn("n")
                            .build();
                }

                List<String> facilities = floodFacilityList.stream()
                        .map(FloodFacility::getFacility)
                        .collect(Collectors.toList());

                return ViewBuildingRes.BuildingData.Inspection.builder()
                        .yn("y")
                        .facilities(facilities)
                        .build();
            }



            public static Inspection of() {
                return Inspection.builder()
                        .facilities(Collections.EMPTY_LIST)
                        .yn("n")
                        .build();
            }

        }


        public static ViewBuildingRes.BuildingData of() {
            return BuildingData.builder()
                    .pk("")
                    .latitude(0.0)
                    .longitude(0.0)
                    .buildingName("")
                    .address("")
                    .year("")
                    .usage("")
                    .belowFloorNum(0)
                    .aboveFloorNum(0)
                    .safety(ViewBuildingRes.BuildingData.Safety.of())
                    .floodHistoryYears(Collections.emptyList())
                    .inspection(ViewBuildingRes.BuildingData.Inspection.of())
                    .build();
        }

        public static ViewBuildingRes.BuildingData of(Building building, List<FloodHistory> floodHistorys,
                                                      SafetyScore safetyScore,
                                                      List<FloodFacility> floodFacilities) throws ParseException {
            return BuildingData.builder()
                    .pk(building.getManagementBuildingRegistryPK())
                    .latitude(defaultIfNull(building.getLatitude(),0.0))
                    .longitude(defaultIfNull(building.getLongitude(),0.0))
                    .buildingName(defaultIfNull(building.getBuildingName(), ""))
                    .address(defaultIfNull(building.getLandLocation(), ""))
                    .year(ofYear(building))
                    .usage(defaultIfNull(building.getMainUseCodeName(), ""))
                    .belowFloorNum(defaultIfNull(building.getBelowGroundFloorCount(), 0))
                    .aboveFloorNum(defaultIfNull(building.getAboveGroundFloorCount(), 0))
                    .safety(ViewBuildingRes.BuildingData.Safety.of(defaultIfNull(safetyScore, new SafetyScore())))
                    .floodHistoryYears(ofFooldYearList(floodHistorys))
                    .inspection(ViewBuildingRes.BuildingData.Inspection.of(floodFacilities))
                    .build();
        }


        public static String ofYear(Building building) throws ParseException {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = dateFormat.parse(building.getUsageApprovalDate());
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            String year = yearFormat.format(date);
            return year;
        }

        public static Integer ofFloodYear(FloodHistory floodHistory){
            return floodHistory.getFloodYear();
        }
        public static List<Integer> ofFooldYearList(List<FloodHistory> floodHistories){
            return floodHistories.stream().map(f -> ofFloodYear(f)).collect(Collectors.toList());
        }
    }
    private static <T> T defaultIfNull(T value, T defaultValue) {
        return (value != null) ? value : defaultValue;
    }

    public static ViewBuildingRes of(RegionNotification regionNotification){
        return ViewBuildingRes.builder()
                .notification(defaultIfNull(regionNotification.getContent(), ""))
                .buildings(List.of())
                .build();
    }

    public static ViewBuildingRes of(List<ViewBuildingRes.BuildingData> buildingData, RegionNotification regionNotification){
        return ViewBuildingRes.builder()
                .notification(defaultIfNull(regionNotification.getContent(), ""))
                .buildings(buildingData)
                .build();
    }


}
