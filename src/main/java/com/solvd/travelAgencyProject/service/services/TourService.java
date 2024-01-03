package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Tour;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class TourService extends BaseService {

    public void createTour() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Tour tour = creationObjectsFromConsole.createNewTourFromConsole();
        if (mainTransportJDBC.getById(tour.getMainTransportId()) == null || tourTypeJDBC.getById(tour.getTourTypeId()) == null) {
            log.info("There is no such data");
            tourJDBC.create(tour).rollback();
        } else {
            tourJDBC.create(tour).commit();
        }
    }

    public void deleteTourById() throws SQLException {
        log.info("Enter the id of the tour you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        tourTypeJDBC.deleteById(id);
    }

    public Tour getTourById() throws SQLException {
        log.info("Enter the id of the tour you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        Tour tour = tourJDBC.getById(id);
        tour.setTourType(tourTypeJDBC.getById(tour.getTourTypeId()));
        tour.setMainTransport(mainTransportJDBC.getById(tour.getMainTransportId()));
        return tour;
    }

    public void showToursByCriteria() {
        log.info("Enter 'cost' or 'tour type' if you want get tours by established criteria");
        String option = CreationObjectsFromConsole.scanner.next();
        switch (option) {
            case ("cost"):
                log.info("Enter the maximum cost of the tour");
                double cost = CreationObjectsFromConsole.scanner.nextDouble();
                tourJDBC.showToursWithArrangedCost(cost);
                break;
            case ("tour type"):
                log.info("Enter the type of the tour");
                String tourType = CreationObjectsFromConsole.scanner.next();
                tourJDBC.showToursWithEstablishedTourType(tourType);
                break;
        }
    }
}
