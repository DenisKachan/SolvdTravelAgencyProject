package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
@Data
public class Hotel {

    private int id;

    private String name;

    private int capacity;

    private int telephoneNumber;

    private HotelChain hotelChain;

    private int hotelChainId;

    public Hotel(int id, String name, int capacity, int telephoneNumber, HotelChain hotelChain) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.telephoneNumber = telephoneNumber;
        this.hotelChain = hotelChain;
    }

    public Hotel() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hotel hotel)) return false;
        return getId() == hotel.getId() && getCapacity() == hotel.getCapacity() && getTelephoneNumber() == hotel.getTelephoneNumber() && getHotelChainId() == hotel.getHotelChainId() && Objects.equals(getName(), hotel.getName()) && Objects.equals(getHotelChain(), hotel.getHotelChain());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCapacity(), getTelephoneNumber(), getHotelChain(), getHotelChainId());
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", telephoneNumber=" + telephoneNumber +
                ", hotelChain=" + hotelChain +
                ", hotelChainId=" + hotelChainId +
                '}';
    }
}
