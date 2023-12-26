package com.solvd.travelAgencyProject.service.consoleScanner;


import com.solvd.travelAgencyProject.domain.*;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.Scanner;

@Log4j2
public final class CreationObjectsFromConsole {

    public static final Scanner scanner = new Scanner(System.in);

    public ClientAgreement createClientAgreementFromConsole() {
        ClientAgreement clientAgreement = new ClientAgreement();
        clientAgreement.setDate(new Date());
        log.info("Enter the conditions of the client agreement");
        clientAgreement.setConditions(scanner.next());
        log.info("Enter the id of the client");
        clientAgreement.setClientId(scanner.nextInt());
        log.info("Enter the id of the discount");
        clientAgreement.setDiscountId(scanner.nextInt());
        log.info("Enter the id of the travel agent");
        clientAgreement.setTravelAgentId(scanner.nextInt());
        log.info("Enter the id of the tour");
        clientAgreement.setTourId(scanner.nextInt());
        log.info("Enter the id of the tour type");
        clientAgreement.setTourTypeId(scanner.nextInt());
        log.info("Enter the id of the main transport");
        clientAgreement.setMainTransportId(scanner.nextInt());
        return clientAgreement;
    }

    public Client createClientFromConsole() {
        Client client = new Client();
        log.info("Enter the phone number of the client");
        client.setPhoneNumber(scanner.nextInt());
        log.info("Enter the name of the client");
        client.setName(scanner.next());
        log.info("Enter the surname of the client");
        client.setSurname(scanner.next());
        log.info("Enter the id of the discount");
        client.setDiscountId(scanner.nextInt());
        return client;
    }

    public Country createNewCountryFromConsole() {
        Country country = new Country();
        log.info("Enter the name of the country");
        country.setName(scanner.next());
        return country;
    }

    public Discount createNewDiscountFromConsole() {
        Discount discount = new Discount();
        log.info("Enter the amount of tours needed for the discount");
        discount.setAmountOfTours(scanner.nextInt());
        log.info("Enter the amount of discount");
        discount.setAmountOfDiscount(scanner.nextInt());
        return discount;
    }

    public HotelChain createNewHotelChainFromConsole() {
        HotelChain hotelChain = new HotelChain();
        log.info("Enter the name of the hotel chain");
        hotelChain.setName(scanner.next());
        return hotelChain;
    }

    public Hotel createNewHotelFromConsole() {
        Hotel hotel = new Hotel();
        log.info("Enter the name of the hotel");
        hotel.setName(scanner.next());
        log.info("Enter the capacity of the hotel");
        hotel.setCapacity(scanner.nextInt());
        log.info("Enter the phone number of the hotel");
        hotel.setTelephoneNumber(scanner.nextInt());
        log.info("Enter the id of the hotel chain");
        hotel.setHotelChainId(scanner.nextInt());
        return hotel;
    }

    public MainTransport createNewMainTransportFromConsole() {
        MainTransport mainTransport = new MainTransport();
        log.info("Enter the name of the main transport");
        mainTransport.setName(scanner.next());
        return mainTransport;
    }

    public Tour createNewTourFromConsole() {
        Tour tour = new Tour();
        log.info("Enter the name of the tour");
        tour.setName(scanner.next());
        log.info("Enter the cost of the tour");
        tour.setCost(scanner.nextDouble());
        log.info("Enter the id og the tour type");
        tour.setTourTypeId(scanner.nextInt());
        log.info("Enter the id of the main transport");
        tour.setMainTransportId(scanner.nextInt());
        return tour;
    }

    public TourType createNewTourTypeFromConsole() {
        TourType tourType = new TourType();
        log.info("Enter the name of the tour type");
        tourType.setName(scanner.next());
        return tourType;
    }

    public TravelAgent createNewTravelAgentFromConsole() {
        TravelAgent travelAgent = new TravelAgent();
        log.info("Enter the name of the travel agent");
        travelAgent.setName(scanner.next());
        log.info("Enter the surname of the travel agent");
        travelAgent.setSurname(scanner.next());
        log.info("Enter the id of the tour type");
        travelAgent.setTourTypeId(scanner.nextInt());
        return travelAgent;
    }
}
