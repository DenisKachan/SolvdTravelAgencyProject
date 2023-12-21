package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.domain.HotelChain;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.persistence.repositories.HotelChainRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class HotelChainService {

    private HotelChainRepository hotelChainRepository;

    public HotelChainService() {
        this.hotelChainRepository = new HotelChainRepository();
    }

    public void createHotelChain() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        HotelChain hotelChain = creationObjectsFromConsole.createNewHotelChainFromConsole();
        hotelChainRepository.create(hotelChain);
    }
}
