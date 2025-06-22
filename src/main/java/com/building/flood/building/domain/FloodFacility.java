package com.building.flood.building.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "flood_facility")
public class FloodFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flood_facility_id")
    private Long id;
    @Column(name="facility")
    private String facility;
    @Column(name="address")
    private String address;

}
