package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.domain.Country;
import com.solvd.travelAgencyProject.persistence.repositories.ClientAgreementRepository;
import com.solvd.travelAgencyProject.persistence.repositories.CountryRepository;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class ClientAgreementService {

    private ClientAgreementRepository clientAgreementRepository;

    public ClientAgreementService() {
        this.clientAgreementRepository = new ClientAgreementRepository();
    }

    public void createClientAgreement() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        ClientAgreement clientAgreement = creationObjectsFromConsole.createClientAgreementFromConsole();
        clientAgreementRepository.create(clientAgreement);
    }

}
