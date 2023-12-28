package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.persistence.repositories.*;

public class BaseService {

    protected ClientAgreementJDBCImpl clientAgreementJDBC;
    protected ClientJDBCImpl clientJDBC;
    protected CountryJDBCImpl countryJDBC;
    protected DiscountJDBCImpl discountJDBC;
    protected HotelChainJDBCImpl hotelChainJDBC;
    protected HotelJDBCImpl hotelJDBC;
    protected MainTransportJDBCImpl mainTransportJDBC;
    protected TourJDBCImpl tourJDBC;
    protected TourTypeJDBCImpl tourTypeJDBC;
    protected TravelAgentJDBCImpl travelAgentJDBC;

    public BaseService() {
        this.clientAgreementJDBC = new ClientAgreementJDBCImpl();
        this.clientJDBC = new ClientJDBCImpl();
        this.countryJDBC = new CountryJDBCImpl();
        this.discountJDBC = new DiscountJDBCImpl();
        this.hotelChainJDBC = new HotelChainJDBCImpl();
        this.hotelJDBC = new HotelJDBCImpl();
        this.mainTransportJDBC = new MainTransportJDBCImpl();
        this.tourJDBC = new TourJDBCImpl();
        this.tourTypeJDBC = new TourTypeJDBCImpl();
        this.travelAgentJDBC = new TravelAgentJDBCImpl();
    }
}
