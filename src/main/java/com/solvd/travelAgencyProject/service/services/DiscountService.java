package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Discount;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class DiscountService extends BaseService {

    public void createDiscount() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Discount discount = creationObjectsFromConsole.createNewDiscountFromConsole();
        discountJDBC.create(discount).commit();
    }

    public void deleteDiscountById() throws SQLException {
        log.info("Enter the id of the discount you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        discountJDBC.deleteById(id);
    }

    public Discount getDiscountById() throws SQLException {
        log.info("Enter the id of the discount you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return discountJDBC.getById(id);
    }
}
