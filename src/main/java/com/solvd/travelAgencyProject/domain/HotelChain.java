package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class HotelChain {

    private int id;

    private String name;

    public HotelChain(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public HotelChain() {

    }
}
