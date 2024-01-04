package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import jakarta.xml.bind.JAXBException;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class HotelService extends BaseService {


    public void createHotel() throws SQLException, JAXBException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Hotel hotel = creationObjectsFromConsole.createNewHotelFromConsole();
        if (hotelChainJDBC.getById(hotel.getHotelChainId()) == null) {
            log.info("There is no such data");
            hotelJDBC.create(hotel).rollback();
        } else {
            hotelJDBC.create(hotel).commit();
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
