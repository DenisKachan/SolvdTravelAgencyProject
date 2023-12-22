package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Discount {

    private int id;

    private int amountOfTours;

    private double amountOfDiscount;

    public Discount(int id, int amountOfTours, double amountOfDiscount) {
        this.id = id;
        this.amountOfTours = amountOfTours;
        this.amountOfDiscount = amountOfDiscount;
    }

    public Discount() {

    }
}
