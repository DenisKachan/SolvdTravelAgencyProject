package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.TourType;
import com.solvd.travelAgencyProject.persistence.utils.DOMParser;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.menu.MainMenu;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
public class TourTypeService extends BaseService {

    public void createTourType() throws SQLException, IOException, SAXException {
        if (MainMenu.domParserFlag) {
            File tourTypeFile = new File(propertyReader.getProperty("tourTypeFile"));
            DOMParser domParser = new DOMParser();
            Document document = domParser.parse(tourTypeFile);
            TourType tourType = new TourType();
            NodeList names = document.getElementsByTagName("name");
            Node name = names.item(0);
            tourType.setName(name.getTextContent());
            tourTypeJDBC.create(tourType).commit();
        } else {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            TourType tourType = creationObjectsFromConsole.createNewTourTypeFromConsole();
            tourTypeJDBC.create(tourType).commit();
        }
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
