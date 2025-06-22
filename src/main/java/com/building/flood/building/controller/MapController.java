package com.building.flood.building.controller;

import com.building.flood.building.dto.ViewBuildingReq;
import com.building.flood.building.service.MapViewService;
import com.building.flood.common.Response.CommonResult;
import com.building.flood.common.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/map")
@RequiredArgsConstructor
public class MapController extends BaseController {

    private final MapViewService mapViewService;
    @ApiOperation(
            value = "위치에 따른 건물 데이터 조회하기",
            notes = "위도/경도 바운더리, 시군구코드, 법정동 코드로 건물 데이터 조회하기"
    )    @PostMapping(path = "/view")
    public CommonResult viewMapBuilding(ViewBuildingReq buildingReq
//            @RequestParam double lat_n ,
//                                        @RequestParam double lat_s ,
//                                        @RequestParam double lng_n ,
//                                        @RequestParam double lng_s ,
//                                        @RequestParam String cityDistrictCode,
//                                        @RequestParam String legalDongCode
    ) {
        return result(mapViewService.viewMapData(
//                lat_n, lat_s, lng_n, lng_s, cityDistrictCode, legalDongCode
                buildingReq.getLat_n(),
                buildingReq.getLat_s(),
                buildingReq.getLng_n(),
                buildingReq.getLng_s(),
                buildingReq.getCityDistrictCode(),
                buildingReq.getLegalDongCode()
        ));
    }

}
