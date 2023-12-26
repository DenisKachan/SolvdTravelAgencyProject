package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Discount;
import com.solvd.travelAgencyProject.persistence.repositories.DiscountRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class DiscountService {

    private DiscountRepository discountRepository;

    public DiscountService() {
        this.discountRepository = new DiscountRepository();
    }

    public void createDiscount() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Discount discount = creationObjectsFromConsole.createNewDiscountFromConsole();
        discountRepository.create(discount).commit();
    }

    public void deleteDiscountById() throws SQLException {
        log.info("Enter the id of the discount you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        discountRepository.deleteById(id);
    }

    public Discount getDiscountById() throws SQLException {
        log.info("Enter the id of the discount you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return discountRepository.getById(id);
    }
}
