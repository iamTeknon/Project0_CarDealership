package com.automart.jdbc.entities;

import java.math.BigDecimal;

public class Offers {
    private int offerId;
    private int customerId;
    private int vehicleId;
    private BigDecimal offer;
    private String verdict;

    public Offers(){
    }

    public Offers(int offerId, int customerId, int vehicleId, BigDecimal offer, String verdict){
        this.offerId = offerId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.offer = offer;
        this.verdict = verdict;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public BigDecimal getOffer() {
        return offer;
    }

    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Offers["
                + "offerId = " + offerId
                + ", customerId = " + customerId
                + ", vehicleId = " + vehicleId
                + ", offer = " + offer
                + ", verdict = " + verdict
                + "]";
    }

}
