package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import com.solvd.travelAgencyProject.persistence.utils.PropertyReader;

import java.io.File;

public interface FileStrategy {

    PropertyReader propertyReader = new PropertyReader("config.properties");

    File setXmlFile();

    File setJsonFile();
}
