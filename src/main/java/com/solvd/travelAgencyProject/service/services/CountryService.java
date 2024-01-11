package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.persistence.utils.DOMParser;
import com.solvd.travelAgencyProject.persistence.utils.JAXBParser;
import com.solvd.travelAgencyProject.persistence.utils.JacksonParser;
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
public class CountryService extends BaseService {

    File countryFile = new File(propertyReader.getProperty("countryFile"));
    File countryFileJSON = new File(propertyReader.getProperty("countryFileJSON"));


    public void createCountry() throws SQLException, IOException, SAXException, JAXBException {
        if (MainMenu.domParserFlag) {
            DOMParser domParser = new DOMParser();
            Document document = domParser.parse(countryFile);
            Country country = new Country();
            NodeList names = document.getElementsByTagName("name");
            Node name = names.item(0);
            country.setName(name.getTextContent());
            countryJDBC.create(country).commit();
        } else if (MainMenu.jaxbParserFlag) {
            JAXBParser jaxbParser = new JAXBParser();
            Country country = new Country();
            country = (Country) jaxbParser.parseFile(country, countryFile);
            countryJDBC.create(country).commit();
        } else if (MainMenu.jsonParserFlag) {
            JacksonParser jacksonParser = new JacksonParser();
            Country country = new Country();
            country = (Country) jacksonParser.parseFile(country,countryFileJSON);
            countryJDBC.create(country).commit();
        } else {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            Country country = creationObjectsFromConsole.createNewCountryFromConsole();
            countryJDBC.create(country).commit();
        }
    }

    public void deleteCountryById() throws SQLException {
        log.info("Enter the id of the country you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        countryJDBC.deleteById(id);
    }

    public Country getCountryById() throws SQLException {
        log.info("Enter the id of the country you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return countryJDBC.getById(id);
    }
}
