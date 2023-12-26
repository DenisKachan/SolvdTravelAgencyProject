package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.domain.TravelAgent;
import com.solvd.travelAgencyProject.persistence.repositories.TourTypeRepository;
import com.solvd.travelAgencyProject.persistence.repositories.TravelAgentRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class TravelAgentService {

    private TravelAgentRepository travelAgentRepository;

    private TourTypeRepository tourTypeRepository;

    public TravelAgentService() {
        this.travelAgentRepository = new TravelAgentRepository();
        this.tourTypeRepository = new TourTypeRepository();
    }

    public void createTravelAgent() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        TravelAgent travelAgent = creationObjectsFromConsole.createNewTravelAgentFromConsole();
        if (tourTypeRepository.getById(travelAgent.getTourTypeId())==null ){
            log.info("There is no such data");
            travelAgentRepository.create(travelAgent).rollback();
        }
        else {
            travelAgentRepository.create(travelAgent).commit();}
    }

    public void deleteTravelAgentById() throws SQLException {
        log.info("Enter the id of the travel agent you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        travelAgentRepository.deleteById(id);
    }

    public TravelAgent getClientById() throws SQLException {
        log.info("Enter the id of the travel agent you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        TravelAgent travelAgent = travelAgentRepository.getById(id);
        travelAgent.setTourType(tourTypeRepository.getById(travelAgent.getTourTypeId()));
        return travelAgent;
    }
}
