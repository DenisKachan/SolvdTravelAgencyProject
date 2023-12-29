package com.solvd.travelAgencyProject.service.services;

import com.solvd.travelAgencyProject.persistence.repositories.*;

public class BaseService {

    protected ClientAgreementImpl clientAgreementJDBC;
    protected ClientImpl clientJDBC;
    protected CountryImpl countryJDBC;
    protected DiscountImpl discountJDBC;
    protected HotelChainImpl hotelChainJDBC;
    protected HotelImpl hotelJDBC;
    protected MainTransportImpl mainTransportJDBC;
    protected TourImpl tourJDBC;
    protected TourTypeImpl tourTypeJDBC;
    protected TravelAgentImpl travelAgentJDBC;

    public BaseService() {
        this.clientAgreementJDBC = new ClientAgreementImpl();
        this.clientJDBC = new ClientImpl();
        this.countryJDBC = new CountryImpl();
        this.discountJDBC = new DiscountImpl();
        this.hotelChainJDBC = new HotelChainImpl();
        this.hotelJDBC = new HotelImpl();
        this.mainTransportJDBC = new MainTransportImpl();
        this.tourJDBC = new TourImpl();
        this.tourTypeJDBC = new TourTypeImpl();
        this.travelAgentJDBC = new TravelAgentImpl();
    }
}
