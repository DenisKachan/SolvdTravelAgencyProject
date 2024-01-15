package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import java.io.File;

public class TourTypeFileSetter implements FileStrategy {
    @Override
    public File setXmlFile() {
        return new File(propertyReader.getProperty("tourTypeFile"));
    }

    @Override
    public File setJsonFile() {
        return new File(propertyReader.getProperty("tourTypeFileJSON"));
    }
}
