package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Tour {

    private int id;

    private String name;

    private double cost;

    private MainTransport mainTransport;

    private int mainTransportId;

    private TourType tourType;

    private int tourTypeId;

    public Tour(int id, String name, double cost, MainTransport mainTransport, TourType tourType) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.mainTransport = mainTransport;
        this.tourType = tourType;
    }

    public Tour() {

    }
}
