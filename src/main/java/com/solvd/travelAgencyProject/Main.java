package com.solvd.travelAgencyProject;

import com.solvd.travelAgencyProject.service.ClientAgreementService;
import com.solvd.travelAgencyProject.service.CountryService;
import com.solvd.travelAgencyProject.service.TourService;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            TourService tourService = new TourService();
            tourService.showToursByCriteria();
            ClientAgreementService clientAgreementService = new ClientAgreementService();
            clientAgreementService.createClientAgreement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
