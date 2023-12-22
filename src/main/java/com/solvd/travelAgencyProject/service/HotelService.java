package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.Hotel;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.HotelChainRepository;
import com.solvd.travelAgencyProject.persistence.repositories.HotelRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

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
        hotelRepository.create(hotel);
    }

}
