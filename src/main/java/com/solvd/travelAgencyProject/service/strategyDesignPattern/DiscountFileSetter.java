package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import java.io.File;

public class DiscountFileSetter implements FileStrategy {
    @Override
    public File setXmlFile() {
        return new File(propertyReader.getProperty("discountFile"));
    }

    @Override
    public File setJsonFile() {
        return new File(propertyReader.getProperty("discountFileJSON"));
    }
}
