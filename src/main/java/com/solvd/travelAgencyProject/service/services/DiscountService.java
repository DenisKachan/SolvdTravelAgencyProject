package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.domain.Discount;
import com.solvd.travelAgencyProject.persistence.utils.DOMParser;
import com.solvd.travelAgencyProject.persistence.utils.JAXBParser;
import com.solvd.travelAgencyProject.persistence.utils.JacksonParser;
import com.solvd.travelAgencyProject.service.consoleScanner.CreationObjectsFromConsole;
import com.solvd.travelAgencyProject.service.menu.MainMenu;
import jakarta.xml.bind.JAXBException;
import lombok.extern.log4j.Log4j2;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
public class DiscountService extends BaseService {

    File discountFile = new File(propertyReader.getProperty("discountFile"));

    File discountFileJSON = new File(propertyReader.getProperty("discountFileJSON"));

    public void createDiscount() throws SQLException, IOException, SAXException, JAXBException {
        if (MainMenu.domParserFlag) {
            DOMParser domParser = new DOMParser();
            Document document = domParser.parse(discountFile);
            Discount discount = new Discount();
            NodeList tours = document.getElementsByTagName("amountOfTours");
            NodeList discounts = document.getElementsByTagName("amountOfDiscount");
            Node amountOfTours = tours.item(0);
            Node amountOfDiscount = discounts.item(0);
            discount.setAmountOfTours(Integer.parseInt(amountOfTours.getFirstChild().getNodeValue()));
            discount.setAmountOfDiscount(Double.parseDouble(amountOfDiscount.getFirstChild().getNodeValue()));
            discountJDBC.create(discount).commit();
        } else if (MainMenu.jaxbParserFlag) {
            JAXBParser jaxbParser = new JAXBParser();
            Discount discount = new Discount();
            discount = (Discount) jaxbParser.parseFile(discount, discountFile);
            discountJDBC.create(discount).commit();
        } else if (MainMenu.jsonParserFlag) {
            JacksonParser jacksonParser = new JacksonParser();
            Discount discount = new Discount();
            discount = (Discount) jacksonParser.parseFile(discount,discountFileJSON);
            discountJDBC.create(discount).commit();
        } else {
            CreationObjectsFromConsole creationObjectsFromConsole = new CreationObjectsFromConsole();
            Discount discount = creationObjectsFromConsole.createNewDiscountFromConsole();
            discountJDBC.create(discount).commit();
        }
    }

    public void deleteDiscountById() throws SQLException {
        log.info("Enter the id of the discount you want to delete");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        discountJDBC.deleteById(id);
    }

    public Discount getDiscountById() throws SQLException {
        log.info("Enter the id of the discount you want to get");
        int id = CreationObjectsFromConsole.scanner.nextInt();
        return discountJDBC.getById(id);
    }
}
