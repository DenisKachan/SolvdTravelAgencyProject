package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.HotelChain;
import com.solvd.travelAgencyProject.persistence.repositories.HotelChainRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class HotelChainService {

    private HotelChainRepository hotelChainRepository;

    public HotelChainService() {
        this.hotelChainRepository = new HotelChainRepository();
    }

    public void createHotelChain() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        HotelChain hotelChain = creationObjectsFromConsole.createNewHotelChainFromConsole();
        hotelChainRepository.create(hotelChain).commit();
    }

    public void deleteHotelChainById() throws SQLException {
        log.info("Enter the id of the hotel chain you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        hotelChainRepository.deleteById(id);
    }

    public HotelChain getHotelChainById() throws SQLException {
        log.info("Enter the id of the hotel chain you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return hotelChainRepository.getById(id);
    }
}
