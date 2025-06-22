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
@Table(name = "flood_history")
public class FloodHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flood_history_id")
    private Long id;
    @Column(name="address")
    private String address;

    @Column(name = "flood_year") //침수연도
    private Integer floodYear;


}
