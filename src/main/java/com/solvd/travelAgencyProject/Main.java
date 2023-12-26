package com.solvd.travelAgencyProject;

import com.solvd.travelAgencyProject.service.CountryService;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            CountryService countryService = new CountryService();
            countryService.createCountry();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
