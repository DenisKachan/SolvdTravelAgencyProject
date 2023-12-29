package com.solvd.travelAgencyProject.domain;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.Objects;

@Log4j2
@Data
public class ClientAgreement {

    private int id;

    private String conditions;

    private Client client;

    private int ClientId;

    private Discount discount;

    private int discountId;

    private TravelAgent travelAgent;

    private int travelAgentId;

    private Tour tour;

    private int tourId;

    private TourType tourType;

    private int tourTypeId;

    private MainTransport mainTransport;

    private int mainTransportId;

    public ClientAgreement(int id, String conditions, Client client, Discount discount,
                           TravelAgent travelAgent, Tour tour, TourType tourType, MainTransport mainTransport) {
        this.id = id;
        this.conditions = conditions;
        this.client = client;
        this.discount = discount;
        this.travelAgent = travelAgent;
        this.tour = tour;
        this.tourType = tourType;
        this.mainTransport = mainTransport;
    }

    public ClientAgreement() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientAgreement that)) return false;
        return getId() == that.getId() && getClientId() == that.getClientId() && getDiscountId() == that.getDiscountId() && getTravelAgentId() == that.getTravelAgentId() && getTourId() == that.getTourId() && getTourTypeId() == that.getTourTypeId() && getMainTransportId() == that.getMainTransportId() && Objects.equals(getConditions(), that.getConditions()) && Objects.equals(getClient(), that.getClient()) && Objects.equals(getDiscount(), that.getDiscount()) && Objects.equals(getTravelAgent(), that.getTravelAgent()) && Objects.equals(getTour(), that.getTour()) && Objects.equals(getTourType(), that.getTourType()) && Objects.equals(getMainTransport(), that.getMainTransport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getConditions(), getClient(), getClientId(), getDiscount(), getDiscountId(), getTravelAgent(), getTravelAgentId(), getTour(), getTourId(), getTourType(), getTourTypeId(), getMainTransport(), getMainTransportId());
    }

    @Override
    public String toString() {
        return "ClientAgreement{" +
                "id=" + id +
                ", conditions='" + conditions + '\'' +
                ", client=" + client +
                ", ClientId=" + ClientId +
                ", discount=" + discount +
                ", discountId=" + discountId +
                ", travelAgent=" + travelAgent +
                ", travelAgentId=" + travelAgentId +
                ", tour=" + tour +
                ", tourId=" + tourId +
                ", tourType=" + tourType +
                ", tourTypeId=" + tourTypeId +
                ", mainTransport=" + mainTransport +
                ", mainTransportId=" + mainTransportId +
                '}';
    }
}
