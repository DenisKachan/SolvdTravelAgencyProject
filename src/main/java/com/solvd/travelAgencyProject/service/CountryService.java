package com.solvd.travelAgencyProject.service;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class CountryService {

   private CountryRepository countryRepository;

    public CountryService() {
        this.countryRepository = new CountryRepository();
    }

    public void createCountry() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Country country = creationObjectsFromConsole.createNewCountryFromConsole();
        countryRepository.create(country);
    }
}
