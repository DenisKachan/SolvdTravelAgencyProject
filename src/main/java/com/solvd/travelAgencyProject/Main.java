package com.solvd.travelAgencyProject;

import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.menu.MainMenu;
import com.solvd.travelAgencyProject.service.services.ClientAgreementService;
import com.solvd.travelAgencyProject.service.services.TourService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            MainMenu mainMenu = new MainMenu();
           // mainMenu.performSimpleSQLQueryWithEstablishedService();
            mainMenu.startProgram();
            ClientAgreementService clientAgreementService = new ClientAgreementService();
            TourService tourService = new TourService();
            tourService.showToursByCriteria();
            clientAgreementService.createClientAgreement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
