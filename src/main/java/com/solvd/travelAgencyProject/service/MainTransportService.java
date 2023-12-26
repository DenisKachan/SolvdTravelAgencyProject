package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.MainTransport;
import com.solvd.travelAgencyProject.persistence.repositories.MainTransportRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class MainTransportService {

    private MainTransportRepository mainTransportRepository;

    public MainTransportService() {
        this.mainTransportRepository = new MainTransportRepository();
    }

    public void createMainTransport() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        MainTransport mainTransport = creationObjectsFromConsole.createNewMainTransportFromConsole();
        mainTransportRepository.create(mainTransport).commit();
    }

    public void deleteMainTransportById() throws SQLException {
        log.info("Enter the id of the transport you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        mainTransportRepository.deleteById(id);
    }

    public MainTransport getMainTransportById() throws SQLException {
        log.info("Enter the id of the transport you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return mainTransportRepository.getById(id);
    }
}
