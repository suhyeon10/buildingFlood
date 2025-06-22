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
@Table(name = "safety_score")
public class SafetyScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "safety_score_id")
    private Long id;
    @OneToOne
    @JoinColumn(name="management_building_registry_PK")
    private Building building;
    @Column(name="score")
    private Integer score;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private SafetyStatus status;

    public static SafetyScore from(int score, Building building){
        return SafetyScore.builder()
                .score(score)
                .status(SafetyStatus.getSafetyStatus(score))
                .building(building)
                .build();
    }
}
