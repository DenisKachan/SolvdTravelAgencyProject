package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import java.io.File;

public class HotelChainFileSetter implements FileStrategy {
    @Override
    public File setXmlFile() {
        return new File(propertyReader.getProperty("hotelChainFile"));
    }

    @Override
    public File setJsonFile() {
        return new File(propertyReader.getProperty("hotelChainFileJSON"));
    }
}
