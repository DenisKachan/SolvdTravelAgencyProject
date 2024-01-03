package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.TourType;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.SQLException;

@Log4j2
public class TourTypeService extends BaseService {

    public void createTourType() throws SQLException, IOException, SAXException {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            TourType tourType = creationObjectsFromConsole.createNewTourTypeFromConsole();
            tourTypeJDBC.create(tourType).commit();
    }

    public void deleteTourTypeById() throws SQLException {
        log.info("Enter the id of the tour type you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        tourTypeJDBC.deleteById(id);
    }

    public TourType getTourTypeById() throws SQLException {
        log.info("Enter the id of the tour type you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return tourTypeJDBC.getById(id);
    }
}
