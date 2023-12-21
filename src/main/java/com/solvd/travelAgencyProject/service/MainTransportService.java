package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.MainTransport;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.MainTransportRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class MainTransportService {

    private MainTransportRepository mainTransportRepository;

    public MainTransportService() {
        this.mainTransportRepository = new MainTransportRepository();
    }

    public void createMainTransport() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        MainTransport mainTransport = creationObjectsFromConsole.createNewMainTransportFromConsole();
        mainTransportRepository.create(mainTransport);
    }
}
