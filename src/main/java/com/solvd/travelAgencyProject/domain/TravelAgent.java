package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class TravelAgent {

    private int id;

    private String name;

    private String surname;

    private TourType tourType;

    public TravelAgent(int id, String name, String surname, TourType tourType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tourType = tourType;
    }

    public TravelAgent() {

    }
}
