package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Country {

    private int id;

    private String name;

    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country() {

    }
}
