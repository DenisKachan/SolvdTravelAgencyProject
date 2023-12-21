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

    private TourType tourType;
}
