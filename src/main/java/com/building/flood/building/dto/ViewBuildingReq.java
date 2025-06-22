package com.building.flood.building.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.RequestParam;

@Getter
@Builder
@AllArgsConstructor
public class ViewBuildingReq {
    private double lat_n;
    private double lat_s;
    private double lng_n;
    private double lng_s;
    private String cityDistrictCode;
    private String legalDongCode;
}
