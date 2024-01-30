package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.persistence.utils.JacksonParser;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.menu.MainMenu;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
public class HotelService extends BaseService {

    File hotelFileJSON = new File(propertyReader.getProperty("hotelFileJSON"));


    public void createHotel() throws SQLException, IOException {
      if (MainMenu.jsonParserFlag) {
        JacksonParser jacksonParser = new JacksonParser();
        Hotel hotel = new Hotel();
        hotel = (Hotel) jacksonParser.parseFile(hotel,hotelFileJSON);
          System.out.println(hotel.getHotelChainId());
          if (hotelChainJDBC.getById(hotel.getHotelChainId()) == null) {
              log.info("There is no such data");
              hotelJDBC.create(hotel).rollback();
          } else {
              hotelJDBC.create(hotel).commit();
          }
    } else {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Hotel hotel = creationObjectsFromConsole.createNewHotelFromConsole();
        if (hotelChainJDBC.getById(hotel.getHotelChainId()) == null) {
            log.info("There is no such data");
            hotelJDBC.create(hotel).rollback();
        } else {
            hotelJDBC.create(hotel).commit();
        }
      }
    }

    public void deleteHotelById() throws SQLException {
        log.info("Enter the id of the hotel you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        hotelJDBC.deleteById(id);
    }

    public Hotel getHotelById() throws SQLException {
        log.info("Enter the id of the hotel you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        Hotel hotel = hotelJDBC.getById(id);
        hotel.setHotelChain(hotelChainJDBC.getById(hotel.getHotelChainId()));
        return hotel;
    }
}
