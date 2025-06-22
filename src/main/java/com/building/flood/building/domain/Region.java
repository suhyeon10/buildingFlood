package com.building.flood.building.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_id")
    private Long id;
    @Column(name = "city_district_code")  // 시군구_코드
    private String cityDistrictCode;
    @Column(name = "legal_dong_code")  // 법정동_코드
    private String legalDongCode;
}
