package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.domain.Tour;
import com.solvd.travelAgencyProject.persistence.repositories.MainTransportRepository;
import com.solvd.travelAgencyProject.persistence.repositories.TourRepository;
import com.solvd.travelAgencyProject.persistence.repositories.TourTypeRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class TourService {

    private TourRepository tourRepository;

    private MainTransportRepository mainTransportRepository;

    private TourTypeRepository tourTypeRepository;

    public TourService() {
        this.tourRepository = new TourRepository();
        this.mainTransportRepository = new MainTransportRepository();
        this.tourTypeRepository = new TourTypeRepository();
    }

    public void createTour() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Tour tour = creationObjectsFromConsole.createNewTourFromConsole();
        if (mainTransportRepository.getById(tour.getMainTransportId())==null || tourTypeRepository.getById(tour.getTourTypeId())==null){
            log.info("There is no such data");
            tourRepository.create(tour).rollback();
        }
        else {
            tourRepository.create(tour).commit();}
    }

    public void deleteTourById() throws SQLException {
        log.info("Enter the id of the tour you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        tourTypeRepository.deleteById(id);
    }

    public Tour getTourById() throws SQLException {
        log.info("Enter the id of the tour you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        Tour tour = tourRepository.getById(id);
        tour.setTourType(tourTypeRepository.getById(tour.getTourTypeId()));
        tour.setMainTransport(mainTransportRepository.getById(tour.getMainTransportId()));
        return tour;
    }
}
