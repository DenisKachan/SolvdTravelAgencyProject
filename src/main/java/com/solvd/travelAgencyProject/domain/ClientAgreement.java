package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
@Data
public class ClientAgreement {

    private int id;

    private Date date;

    private String conditions;

    private Client client;

    private Discount discount;

    private TravelAgent travelAgent;

    private Tour tour;

    private TourType tourType;

    private MainTransport mainTransport;
}
