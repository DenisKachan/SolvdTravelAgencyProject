package com.solvd.travelAgencyProject;

import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.menu.MainMenu;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        try (CreationObjectsFromConsole.scanner) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.startProgram();
            mainMenu.chooseTypeOfProgramUser();
            mainMenu.chooseActions();

        } catch (SQLException ignored) {
        }

    }
}
