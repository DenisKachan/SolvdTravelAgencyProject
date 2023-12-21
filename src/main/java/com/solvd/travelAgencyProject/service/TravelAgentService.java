package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.TravelAgent;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.TravelAgentRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class TravelAgentService {

    private TravelAgentRepository travelAgentRepository;

    public TravelAgentService() {
        this.travelAgentRepository = new TravelAgentRepository();
    }

    public void createTravelAgent() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        TravelAgent travelAgent = creationObjectsFromConsole.createNewTravelAgentFromConsole();
        travelAgentRepository.create(travelAgent);
    }
}
