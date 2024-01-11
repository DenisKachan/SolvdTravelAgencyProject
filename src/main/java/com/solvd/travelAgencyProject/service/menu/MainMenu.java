package com.solvd.travelAgencyProject.service.menu;

import com.solvd.travelAgencyProject.persistence.utils.MybatisConfiguration;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.services.ClientAgreementService;
import com.solvd.travelAgencyProject.service.services.TourService;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

@Log4j2
public class MainMenu {

    private static Class<?> aClass;

    private static Object object;

    public static boolean userFlag = false;

    public static boolean adminFlag = false;

    public static boolean domParserFlag = false;

    public static boolean jaxbParserFlag = false;

    public static boolean jsonParserFlag = false;

    public void startProgram() {
        log.info("PLease enter 'mybatis' if you want to interact with database using this framework" +
                "or press any other key if it is not needed");
        String option = CreationObjectsFromConsole.scanner.next();
        if (option.equals("mybatis")) {
            MybatisConfiguration.flag = true;
        }
    }

    public void chooseTypeOfProgramUser() {
        log.info("Enter 'user' or 'admin' to use the functionality of the program");
        String option = CreationObjectsFromConsole.scanner.next();
        if (option.equals("admin")) {
            adminFlag = true;
        } else if (option.equals("user")) {
            userFlag = true;
        } else {
            log.info("You entered the wrong data");
        }

    }

    public void chooseActions() throws SQLException {
        if (adminFlag) {
            log.info("Enter the number of the wanted action from the list");
            log.info("1. Perform simple query with the help of service");
            log.info("2. Restart");
            int option = CreationObjectsFromConsole.scanner.nextInt();
            switch (option) {
                case (1):
                    performSimpleSQLQueryWithEstablishedService();
                    break;
                case (2):
                    MybatisConfiguration.flag = false;
                    startProgram();
                    break;
            }
        } else if (userFlag) {
            TourService tourService = new TourService();
            ClientAgreementService clientAgreementService = new ClientAgreementService();
            log.info("Enter the number of the wanted action from the list");
            log.info("1. Show tours by any criteria");
            log.info("2. To order a certain tour");
            int option = CreationObjectsFromConsole.scanner.nextInt();
            switch (option) {
                case (1):
                    tourService.showToursByCriteria();
                    break;
                case (2):
                    clientAgreementService.createClientAgreement();
                    break;
            }
        } else {
            log.info("You have no access");
        }
    }


    private Object createAnObjectOfACertainClass() {
        log.info("Enter the name of the service class");
        String name = "com.solvd.travelAgencyProject.service.services." + CreationObjectsFromConsole.scanner.next();
        try {
            aClass = Class.forName(name);
            object = aClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return object;
    }

    public void performSimpleSQLQueryWithEstablishedService() {
        if (object == null) {
            createAnObjectOfACertainClass();
        }
        try {
            log.info("Enter the name of the method you want to invoke");
            String methodName = CreationObjectsFromConsole.scanner.next();
            if (methodName.contains("create")) {
                log.info("Enter the number of the wanted option from the list");
                log.info("1. Create instance using xml and dom parser");
                log.info("2. Create instance using xml and jaxb parser");
                log.info("3. Create instance using json and Jackson parser");
                log.info("4. Create instance using console");
                int option = CreationObjectsFromConsole.scanner.nextInt();
                if (option == 1) {
                    domParserFlag = true;
                } else if (option == 2) {
                    jaxbParserFlag = true;
                } else if (option == 3) {
                    jsonParserFlag = true;
                }
            }
            Method method = aClass.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
