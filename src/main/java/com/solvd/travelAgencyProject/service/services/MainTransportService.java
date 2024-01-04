package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.MainTransport;
import com.solvd.travelAgencyProject.persistence.utils.DOMParser;
import com.solvd.travelAgencyProject.persistence.utils.JAXBParser;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.menu.MainMenu;
import jakarta.xml.bind.JAXBException;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
public class MainTransportService extends BaseService {

    File mainTransportFile = new File(propertyReader.getProperty("mainTransportFile"));

    public void createMainTransport() throws SQLException, IOException, SAXException, JAXBException {
        if (MainMenu.domParserFlag) {
            DOMParser domParser = new DOMParser();
            Document document = domParser.parse(mainTransportFile);
            MainTransport mainTransport = new MainTransport();
            NodeList names = document.getElementsByTagName("name");
            Node name = names.item(0);
            mainTransport.setName(name.getTextContent());
            mainTransportJDBC.create(mainTransport).commit();
        } else if (MainMenu.jaxbParserFlag) {
            JAXBParser jaxbParser = new JAXBParser();
            MainTransport mainTransport = new MainTransport();
            mainTransport = (MainTransport) jaxbParser.parseFile(mainTransport, mainTransportFile);
            mainTransportJDBC.create(mainTransport).commit();
        } else {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            MainTransport mainTransport = creationObjectsFromConsole.createNewMainTransportFromConsole();
            mainTransportJDBC.create(mainTransport).commit();
        }
    }

    public void deleteMainTransportById() throws SQLException {
        log.info("Enter the id of the transport you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        mainTransportJDBC.deleteById(id);
    }

    public MainTransport getMainTransportById() throws SQLException {
        log.info("Enter the id of the transport you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return mainTransportJDBC.getById(id);
    }
}
