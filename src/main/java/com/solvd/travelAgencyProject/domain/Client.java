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
}
