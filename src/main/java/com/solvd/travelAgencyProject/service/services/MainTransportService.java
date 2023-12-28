package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.MainTransport;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class MainTransportService extends BaseService {

    public void createMainTransport() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        MainTransport mainTransport = creationObjectsFromConsole.createNewMainTransportFromConsole();
        mainTransportJDBC.create(mainTransport).commit();
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
