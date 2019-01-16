package com.hackathon.filighbooking.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TripModel implements Serializable{
    private String originPlaceID;
    private String destinationPlaceID;
    private Date departureDay;
    private Date returnDay;
    private Flight flightOutward;
    private Flight flightReturn;
    private boolean isReturnFlight;
    private int numOfPassenger;

    public TripModel(String originPlaceID, String destinationPlaceID, Date departureDay, Date returnDay, boolean isReturnFlight, int numOfPassenger) {
        this.originPlaceID = originPlaceID;
        this.destinationPlaceID = destinationPlaceID;
        this.departureDay = departureDay;
        this.returnDay = returnDay;
        this.isReturnFlight = isReturnFlight;
        this.numOfPassenger = numOfPassenger;
    }

    public Flight getFlightOutward() {
        return flightOutward;
    }

    public void setFlightOutward(Flight flightOutward) {
        this.flightOutward = flightOutward;
    }

    public Flight getFlightReturn() {
        return flightReturn;
    }

    public void setFlightReturn(Flight flightReturn) {
        this.flightReturn = flightReturn;
    }

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

    public boolean isReturnFlight() {
        return isReturnFlight;
    }

    public void setReturnFlight(boolean returnFlight) {
        isReturnFlight = returnFlight;
    }

    public int getNumOfPassenger() {
        return numOfPassenger;
    }

    public void setNumOfPassenger(int numOfPassenger) {
        this.numOfPassenger = numOfPassenger;
    }

    public Date getDepartureDay() {
        return departureDay;
    }

    public void setDepartureDay(Date departureDay) {
        this.departureDay = departureDay;
    }

    public Date getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Date returnDay) {
        this.returnDay = returnDay;
    }

}
