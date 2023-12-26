package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
@Data
public class Client {

    private int id;

    private int phoneNumber;

    private String name;

    private String surname;

    private Discount discount;

    private int discountId;

    public Client(int id, int phoneNumber, String name, String surname, Discount discount) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.discount = discount;
    }

    public Client() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return getId() == client.getId() && getPhoneNumber() == client.getPhoneNumber()
                && getDiscountId() == client.getDiscountId() && Objects.equals(getName(), client.getName())
                && Objects.equals(getSurname(), client.getSurname()) && Objects.equals(getDiscount(), client.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPhoneNumber(), getName(), getSurname(), getDiscount(), getDiscountId());
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", phoneNumber=" + phoneNumber +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", discount=" + discount +
                ", discountId=" + discountId +
                '}';
    }
}
