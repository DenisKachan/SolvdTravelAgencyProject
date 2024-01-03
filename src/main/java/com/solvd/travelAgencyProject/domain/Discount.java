package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discount discount)) return false;
        return getId() == discount.getId() && getAmountOfTours() == discount.getAmountOfTours() && Double.compare(getAmountOfDiscount(), discount.getAmountOfDiscount()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmountOfTours(), getAmountOfDiscount());
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", amountOfTours=" + amountOfTours +
                ", amountOfDiscount=" + amountOfDiscount +
                '}';
    }
}
