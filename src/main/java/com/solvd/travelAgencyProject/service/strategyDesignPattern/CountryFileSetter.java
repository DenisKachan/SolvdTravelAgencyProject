package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import java.io.File;

public class CountryFileSetter implements FileStrategy {
    @Override
    public File setXmlFile() {
        return new File(propertyReader.getProperty("countryFile"));
    }

    @Override
    public File setJsonFile() {
        return new File(propertyReader.getProperty("countryFileJSON"));
    }
}
