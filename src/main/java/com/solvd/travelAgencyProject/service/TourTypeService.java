package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.TourType;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.TourTypeRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class TourTypeService {

    private TourTypeRepository tourTypeRepository;

    public TourTypeService() {
        this.tourTypeRepository = new TourTypeRepository();
    }

    public void createTourType() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        TourType tourType = creationObjectsFromConsole.createNewTourTypeFromConsole();
        tourTypeRepository.create(tourType);
    }
}
