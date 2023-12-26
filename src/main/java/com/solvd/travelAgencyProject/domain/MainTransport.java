package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainTransport that)) return false;
        return getId() == that.getId() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "MainTransport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
