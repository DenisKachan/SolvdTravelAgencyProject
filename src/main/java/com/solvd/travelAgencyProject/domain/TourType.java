package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
@Data
public class TourType {

    private int id;

    private String name;

    public TourType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TourType() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TourType tourType)) return false;
        return getId() == tourType.getId() && Objects.equals(getName(), tourType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "TourType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
