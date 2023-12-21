package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.Discount;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.DiscountRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class DiscountService {

    private DiscountRepository discountRepository;

    public DiscountService() {
        this.discountRepository = new DiscountRepository();
    }

    public void createDiscount() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Discount discount = creationObjectsFromConsole.createNewDiscountFromConsole();
        discountRepository.create(discount);
    }
}
