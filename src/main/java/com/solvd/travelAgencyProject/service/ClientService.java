package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.Client;
import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.persistence.repositories.ClientRepository;
import com.solvd.travelAgencyProject.persistence.repositories.DiscountRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class ClientService {

    private ClientRepository clientRepository;
    private DiscountRepository discountRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
        this.discountRepository = new DiscountRepository();
    }

    public void createClient() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        Client client = creationObjectsFromConsole.createClientFromConsole();
        if (discountRepository.getById(client.getDiscountId())==null ){
            log.info("There is no such data");
            clientRepository.create(client).rollback();
        }
        else {
            clientRepository.create(client).commit();}
    }

    public void deleteClientById() throws SQLException {
        log.info("Enter the id of the client you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        clientRepository.deleteById(id);
    }

    public Client getClientById() throws SQLException {
        log.info("Enter the id of the client you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        Client client = clientRepository.getById(id);
        client.setDiscount(discountRepository.getById(client.getDiscountId()));
        return client;
    }
}
