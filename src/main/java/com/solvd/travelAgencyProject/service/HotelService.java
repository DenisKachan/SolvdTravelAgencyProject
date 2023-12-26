package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.persistence.repositories.HotelChainRepository;
import com.solvd.travelAgencyProject.persistence.repositories.HotelRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class HotelService {

    private HotelRepository hotelRepository;

    private HotelChainRepository hotelChainRepository;

    public HotelService() {
        this.hotelRepository = new HotelRepository();
        this.hotelChainRepository = new HotelChainRepository();
    }

    public void createHotel() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Hotel hotel = creationObjectsFromConsole.createNewHotelFromConsole();
        if (hotelChainRepository.getById(hotel.getHotelChainId())==null ){
            log.info("There is no such data");
            hotelRepository.create(hotel).rollback();
        }
        else {
            hotelRepository.create(hotel).commit();}
    }

    public void deleteHotelById() throws SQLException {
        log.info("Enter the id of the hotel you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        hotelRepository.deleteById(id);
    }

    public Hotel getHotelById() throws SQLException {
        log.info("Enter the id of the hotel you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        Hotel hotel = hotelRepository.getById(id);
        hotel.setHotelChain(hotelChainRepository.getById(hotel.getHotelChainId()));
        return hotel;
    }
}
