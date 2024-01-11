package com.solvd.travelAgencyProject.persistence.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JacksonParser {

    private ObjectMapper mapper = new ObjectMapper();

    public Object parseFile(Object object, File file) throws  IOException {
     return mapper.readValue(file,object.getClass());
    }
}
