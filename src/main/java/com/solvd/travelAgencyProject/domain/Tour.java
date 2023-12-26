package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
@Data
public class Tour {

    private int id;

    private String name;

    private double cost;

    private MainTransport mainTransport;

    private int mainTransportId;

    private TourType tourType;

    private int tourTypeId;

    public Tour(int id, String name, double cost, MainTransport mainTransport, TourType tourType) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.mainTransport = mainTransport;
        this.tourType = tourType;
    }

    public Tour() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tour tour)) return false;
        return getId() == tour.getId() && Double.compare(getCost(), tour.getCost()) == 0 && getMainTransportId() == tour.getMainTransportId() && getTourTypeId() == tour.getTourTypeId() && Objects.equals(getName(), tour.getName()) && Objects.equals(getMainTransport(), tour.getMainTransport()) && Objects.equals(getTourType(), tour.getTourType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCost(), getMainTransport(), getMainTransportId(), getTourType(), getTourTypeId());
    }

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", mainTransport=" + mainTransport +
                ", mainTransportId=" + mainTransportId +
                ", tourType=" + tourType +
                ", tourTypeId=" + tourTypeId +
                '}';
    }
}
