package com.building.flood.building.domain;

public enum SafetyStatus {

    FLOOD_MANAGE(0, 3, "침수관리"),
    FLOOD_CAUTION(4, 6, "침수주의"),
    FLOOD_WARNING(7, 10, "침수경고");

    private final int minRange;
    private final int maxRange;
    private String status;

    SafetyStatus(int minRange, int maxRange, String status) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.status = status;
    }
    public String toString(){
        return status;
    }

    // value가 minRange ~ maxRange 구간에 들어가는 Status를 반환
    public static SafetyStatus getSafetyStatus(int value) {
        for (SafetyStatus status : SafetyStatus.values()) {
            if (value >= status.minRange && value <= status.maxRange) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid value for SafetyStatus: " + value);
    }
}
