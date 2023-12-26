package com.solvd.travelAgencyProject.service;

import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Log4j2
public class MainMenu {

    private static Class<?> aClass;

    private static Object object;

    //   public void startProgram(){
    //       log.info("PLease enter 'mybatis' if you want to interact with database using this framework" +
    //               "or press any other key if it is not needed");
    //   }

    public Object createAnObjectOfACertainClass() {
        log.info("Enter the name of the service class");
        String name = "com.solvd.travelAgencyProject.service" + CreationObjectsFromConsole.scanner.next();
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
            Method method = aClass.getDeclaredMethod(methodName);
            method.setAccessible(true);
            method.invoke(object);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
