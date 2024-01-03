package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.ClientAgreement;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;

@Log4j2
public class ClientAgreementService extends BaseService {

    public void createClientAgreement() throws SQLException {
        CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
        ClientAgreement clientAgreement = creationObjectsFromConsole.createClientAgreementFromConsole();
        if (clientJDBC.getById(clientAgreement.getClientId()) == null || discountJDBC.getById(clientAgreement.getDiscountId()) == null
                || travelAgentJDBC.getById(clientAgreement.getTravelAgentId()) == null || tourJDBC.getById(clientAgreement.getTourId()) == null
                || tourTypeJDBC.getById(clientAgreement.getTourTypeId()) == null || mainTransportJDBC.getById(clientAgreement.getMainTransportId()) == null) {
            log.info("There is no such data");
            clientAgreementJDBC.create(clientAgreement).rollback();
        } else {
            clientAgreementJDBC.create(clientAgreement).commit();
        }
    }

    public void deleteClientAgreementById() throws SQLException {
        log.info("Enter the id of the client agreement you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        clientAgreementJDBC.deleteById(id);
    }

    public ClientAgreement getClientAgreementById() throws SQLException {
        log.info("Enter the id of the client agreement you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        ClientAgreement clientAgreement = clientAgreementJDBC.getById(id);
        clientAgreement.setClient(clientJDBC.getById(clientAgreement.getClientId()));
        clientAgreement.setDiscount(discountJDBC.getById(clientAgreement.getDiscountId()));
        clientAgreement.setTravelAgent(travelAgentJDBC.getById(clientAgreement.getTravelAgentId()));
        clientAgreement.setTour(tourJDBC.getById(clientAgreement.getTourId()));
        clientAgreement.setTourType(tourTypeJDBC.getById(clientAgreement.getTourTypeId()));
        clientAgreement.setMainTransport(mainTransportJDBC.getById(clientAgreement.getMainTransportId()));
        return clientAgreement;
    }
}
