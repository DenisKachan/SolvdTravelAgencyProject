package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Hotel {

    private int id;

    private String name;

    private int capacity;

    private int telephoneNumber;

    private HotelChain hotelChain;

    public Hotel(int id, String name, int capacity, int telephoneNumber, HotelChain hotelChain) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.telephoneNumber = telephoneNumber;
        this.hotelChain = hotelChain;
    }

    public Hotel() {

    }
}
