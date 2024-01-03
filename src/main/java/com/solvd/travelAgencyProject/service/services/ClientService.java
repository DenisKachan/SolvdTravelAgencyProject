package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class ClientService extends BaseService {

    public void createClient() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Client client = creationObjectsFromConsole.createClientFromConsole();
        if (discountJDBC.getById(client.getDiscountId()) == null) {
            log.info("There is no such data");
            clientJDBC.create(client).rollback();
        } else {
            clientJDBC.create(client).commit();
        }
    }

    public void deleteClientById() throws SQLException {
        log.info("Enter the id of the client you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        clientJDBC.deleteById(id);
    }

    public Client getClientById() throws SQLException {
        log.info("Enter the id of the client you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        Client client = clientJDBC.getById(id);
        client.setDiscount(discountJDBC.getById(client.getDiscountId()));
        return client;
    }
}
