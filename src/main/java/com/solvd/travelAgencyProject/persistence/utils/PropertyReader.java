package com.solvd.travelAgencyProject.persistence.utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Properties;

@Log4j2
public class PropertyReader {

    private final Properties properties;

    public PropertyReader(String propertiesFile) {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream(propertiesFile));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
