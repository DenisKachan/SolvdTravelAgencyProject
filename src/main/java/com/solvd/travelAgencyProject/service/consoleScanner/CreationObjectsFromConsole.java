package com.solvd.travelAgencyProject.service.consoleScanner;


import com.solvd.travelAgencyProject.domain.*;
import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

@Log4j2
public final class CreationObjectsFromConsole {

    public static final Scanner scanner = new Scanner(System.in);

    public ClientAgreement createClientAgreementFromConsole(){
        return new ClientAgreement();
    }

    public Client createClientFromConsole(){
        return new Client();
    }

    public Country createNewCountryFromConsole(){
        return new Country();
    }

    public Discount createNewDiscountFromConsole(){
        return new Discount();
    }

    public HotelChain createNewHotelChainFromConsole(){
        return new HotelChain();
    }

    public Hotel createNewHotelFromConsole(){
        return new Hotel();
    }

    public MainTransport createNewMainTransportFromConsole(){
        return new MainTransport();
    }

    public Tour createNewTourFromConsole(){
        return new Tour();
    }

    public TourType createNewTourTypeFromConsole(){
        return new TourType();
    }

    public TravelAgent createNewTravelAgentFromConsole(){
        return new TravelAgent();
    }


}
