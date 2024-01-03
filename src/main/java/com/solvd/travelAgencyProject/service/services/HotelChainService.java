package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.HotelChain;
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
public class HotelChainService extends BaseService {

    public void createHotelChain() throws SQLException, IOException, SAXException {
        if (MainMenu.domParserFlag) {
            File hotelChainFile = new File(propertyReader.getProperty("hotelChainFile"));
            DOMParser domParser = new DOMParser();
            Document document = domParser.parse(hotelChainFile);
            HotelChain hotelChain = new HotelChain();
            NodeList names = document.getElementsByTagName("name");
            Node name = names.item(0);
            hotelChain.setName(name.getTextContent());
            hotelChainJDBC.create(hotelChain).commit();
        } else {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            HotelChain hotelChain = creationObjectsFromConsole.createNewHotelChainFromConsole();
            hotelChainJDBC.create(hotelChain).commit();
        }
    }

    public void deleteHotelChainById() throws SQLException {
        log.info("Enter the id of the hotel chain you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        hotelChainJDBC.deleteById(id);
    }

    public HotelChain getHotelChainById() throws SQLException {
        log.info("Enter the id of the hotel chain you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return hotelChainJDBC.getById(id);
    }
}
