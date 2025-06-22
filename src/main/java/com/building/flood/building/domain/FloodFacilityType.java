package com.building.flood.building.domain;

public enum FloodFacilityType {
    YES(1, 'y'),

    NO(0, 'n');

    private int status;
    private char yn;

    FloodFacilityType(int status, char yn){
        this.status = status;
        this.yn = yn;
    }
}
