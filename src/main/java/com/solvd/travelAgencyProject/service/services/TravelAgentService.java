package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.TravelAgent;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class TravelAgentService extends BaseService {

    public void createTravelAgent() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        TravelAgent travelAgent = creationObjectsFromConsole.createNewTravelAgentFromConsole();
        if (tourTypeJDBC.getById(travelAgent.getTourTypeId()) == null) {
            log.info("There is no such data");
            travelAgentJDBC.create(travelAgent).rollback();
        } else {
            travelAgentJDBC.create(travelAgent).commit();
        }
    }

    public void deleteTravelAgentById() throws SQLException {
        log.info("Enter the id of the travel agent you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        travelAgentJDBC.deleteById(id);
    }

    public TravelAgent getClientById() throws SQLException {
        log.info("Enter the id of the travel agent you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        TravelAgent travelAgent = travelAgentJDBC.getById(id);
        travelAgent.setTourType(tourTypeJDBC.getById(travelAgent.getTourTypeId()));
        return travelAgent;
    }
}
