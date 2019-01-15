package com.hackathon.filighbooking.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TripModel implements Serializable{
    private String originPlaceID;
    private String destinationPlaceID;
    private Date originDay;
    private Date destinationDay;

    private boolean isReturnFlight;
    private List<Flight> listFlight = null;

    public String getOriginPlaceID() {
        return originPlaceID;
    }

    public void setOriginPlaceID(String originPlaceID) {
        this.originPlaceID = originPlaceID;
    }

    public String getDestinationPlaceID() {
        return destinationPlaceID;
    }

    public void setDestinationPlaceID(String destinationPlaceID) {
        this.destinationPlaceID = destinationPlaceID;
    }

    public Date getOrginDay() {
        return originDay;
    }

    public void setOrginDay(Date originDay) {
        this.originDay = originDay;
    }

    public Date getDestinationDay() {
        return destinationDay;
    }

    public void setDestinationDay(Date destinationDay) {
        this.destinationDay = destinationDay;
    }

    public List<Flight> getListFlight() {
        return listFlight;
    }

    public void setListFlight(List<Flight> listFlight) {
        this.listFlight = listFlight;
    }
    
    public boolean isReturnFlight() {
        return isReturnFlight;
    }

    public void setReturnFlight(boolean returnFlight) {
        isReturnFlight = returnFlight;
    }
}
