package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import java.io.File;

public class MainTransportFileSetter implements FileStrategy {
    @Override
    public File setXmlFile() {
        return new File(propertyReader.getProperty("mainTransportFile"));
    }

    @Override
    public File setJsonFile() {
        return new File(propertyReader.getProperty("mainTransportFileJSON"));
    }
}
