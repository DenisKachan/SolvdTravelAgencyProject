package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.persistence.repositories.ClientRepository;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class ClientService {

    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public void createClient() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Client client = creationObjectsFromConsole.createClientFromConsole();
        clientRepository.create(client);
    }
}
