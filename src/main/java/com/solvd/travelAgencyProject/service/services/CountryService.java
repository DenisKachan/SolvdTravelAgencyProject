package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class CountryService extends BaseService {


    public void createCountry() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Country country = creationObjectsFromConsole.createNewCountryFromConsole();
        countryJDBC.create(country).commit();
    }

    public void deleteCountryById() throws SQLException {
        log.info("Enter the id of the country you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        countryJDBC.deleteById(id);
    }

    public Country getCountryById() throws SQLException {
        log.info("Enter the id of the country you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return countryJDBC.getById(id);
    }
}
