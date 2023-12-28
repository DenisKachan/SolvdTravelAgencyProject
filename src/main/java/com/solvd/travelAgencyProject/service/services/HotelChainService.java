package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.HotelChain;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class HotelChainService extends BaseService {

    public void createHotelChain() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        HotelChain hotelChain = creationObjectsFromConsole.createNewHotelChainFromConsole();
        hotelChainJDBC.create(hotelChain).commit();
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
