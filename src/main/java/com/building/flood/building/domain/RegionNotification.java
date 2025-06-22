package com.building.flood.building.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "region_notification")
public class RegionNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "region_notification_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="region_id")
    private Region region;
    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifyAt;
    private LocalDateTime deleteAt;
}
