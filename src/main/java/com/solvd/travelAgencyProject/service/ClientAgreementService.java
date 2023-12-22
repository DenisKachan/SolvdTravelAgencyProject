package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.persistence.repositories.*;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class ClientAgreementService {

    private ClientAgreementRepository clientAgreementRepository;

    private ClientRepository clientRepository;

    private DiscountRepository discountRepository;

    private TravelAgentRepository travelAgentRepository;

    private TourRepository tourRepository;

    private TourTypeRepository tourTypeRepository;

    private MainTransportRepository mainTransportRepository;

    public ClientAgreementService() {
        this.clientAgreementRepository = new ClientAgreementRepository();
        this.clientRepository = new ClientRepository();
        this.discountRepository = new DiscountRepository();
        this.travelAgentRepository = new TravelAgentRepository();
        this.tourRepository = new TourRepository();
        this.tourTypeRepository = new TourTypeRepository();
        this.mainTransportRepository = new MainTransportRepository();
    }

    public void createClientAgreement() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        ClientAgreement clientAgreement = creationObjectsFromConsole.createClientAgreementFromConsole();
        clientAgreementRepository.create(clientAgreement);
    }
}
