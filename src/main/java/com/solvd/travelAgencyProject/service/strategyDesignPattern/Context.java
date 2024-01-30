package com.solvd.travelAgencyProject.service.strategyDesignPattern;

import com.solvd.travelAgencyProject.service.menu.MainMenu;

import java.io.File;

public class Context {

    private FileStrategy fileStrategy;

    public Context(FileStrategy fileStrategy) {
        this.fileStrategy = fileStrategy;
    }

    public File executeStrategy() {
        if (MainMenu.jaxbParserFlag)
            return fileStrategy.setXmlFile();
        else {
            return fileStrategy.setJsonFile();
        }
    }
}
