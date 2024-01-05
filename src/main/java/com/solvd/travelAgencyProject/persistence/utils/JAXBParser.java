package com.solvd.travelAgencyProject.persistence.utils;


import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class JAXBParser {

    private JAXBContext jaxbContext;

    public Object parseFile(Object object, File file) throws JAXBException {
        jaxbContext = JAXBContext.newInstance(object.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(file);
    }
}
