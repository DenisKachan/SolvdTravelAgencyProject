package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
@Data
public class TravelAgent {

    private int id;

    private String name;

    private String surname;

    private TourType tourType;

    private int tourTypeId;

    public TravelAgent(int id, String name, String surname, TourType tourType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tourType = tourType;
    }

    public TravelAgent() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelAgent that)) return false;
        return getId() == that.getId() && getTourTypeId() == that.getTourTypeId() && Objects.equals(getName(), that.getName()) && Objects.equals(getSurname(), that.getSurname()) && Objects.equals(getTourType(), that.getTourType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getTourType(), getTourTypeId());
    }

    @Override
    public String toString() {
        return "TravelAgent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tourType=" + tourType +
                ", tourTypeId=" + tourTypeId +
                '}';
    }
}
