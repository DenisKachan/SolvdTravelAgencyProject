package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class MainTransport {

    private int id;

    private String name;

    public MainTransport(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MainTransport() {

    }
}
