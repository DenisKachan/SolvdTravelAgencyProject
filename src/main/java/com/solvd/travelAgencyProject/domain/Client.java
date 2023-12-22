package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
public class Client {

    private int id;

    private int phoneNumber;

    private String name;

    private String surname;

    private Discount discount;

    public Client(int id, int phoneNumber, String name, String surname, Discount discount) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.discount = discount;
    }

    public Client() {

    }
}
