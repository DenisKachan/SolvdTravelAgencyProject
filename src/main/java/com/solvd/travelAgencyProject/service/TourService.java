package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.Tour;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.TourRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class TourService {

    private TourRepository tourRepository;

    public TourService() {
        this.tourRepository = new TourRepository();
    }

    public void createTour() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Tour tour = creationObjectsFromConsole.createNewTourFromConsole();
        tourRepository.create(tour);
    }
}
