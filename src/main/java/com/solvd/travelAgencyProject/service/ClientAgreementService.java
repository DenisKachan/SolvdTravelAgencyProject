package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.persistence.ConnectionPool;
import com.solvd.travelAgencyProject.persistence.repositories.*;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
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
        if (clientRepository.getById(clientAgreement.getClientId())==null || discountRepository.getById(clientAgreement.getDiscountId())==null
       || travelAgentRepository.getById(clientAgreement.getTravelAgentId())==null || tourRepository.getById(clientAgreement.getTourId())==null
        || tourTypeRepository.getById(clientAgreement.getTourTypeId())==null || mainTransportRepository.getById(clientAgreement.getMainTransportId())==null){
            log.info("There is no such data");
            clientAgreementRepository.create(clientAgreement).rollback();
        }
        else {
        clientAgreementRepository.create(clientAgreement).commit();}
    }

    public void deleteClientAgreementById() throws SQLException {
        log.info("Enter the id of the client agreement you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        clientAgreementRepository.deleteById(id);
    }

    public ClientAgreement getClientAgreementById() throws SQLException {
        log.info("Enter the id of the client agreement you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        ClientAgreement clientAgreement = clientAgreementRepository.getById(id);
        clientAgreement.setClient(clientRepository.getById(clientAgreement.getClientId()));
        clientAgreement.setDiscount(discountRepository.getById(clientAgreement.getDiscountId()));
        clientAgreement.setTravelAgent(travelAgentRepository.getById(clientAgreement.getTravelAgentId()));
        clientAgreement.setTour(tourRepository.getById(clientAgreement.getTourId()));
        clientAgreement.setTourType(tourTypeRepository.getById(clientAgreement.getTourTypeId()));
        clientAgreement.setMainTransport(mainTransportRepository.getById(clientAgreement.getMainTransportId()));
        return clientAgreement;
    }
}
