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

    private int ClientId;

    private Discount discount;

    private int discountId;

    private TravelAgent travelAgent;

    private int travelAgentId;

    private Tour tour;

    private int tourId;

    private TourType tourType;

    private int tourTypeId;

    private MainTransport mainTransport;

    private int mainTransportId;

    public ClientAgreement(int id, Date date, String conditions, Client client, Discount discount,
                           TravelAgent travelAgent, Tour tour, TourType tourType, MainTransport mainTransport) {
        this.id = id;
        this.date = date;
        this.conditions = conditions;
        this.client = client;
        this.discount = discount;
        this.travelAgent = travelAgent;
        this.tour = tour;
        this.tourType = tourType;
        this.mainTransport = mainTransport;
    }

    public ClientAgreement() {
    }
}
