package com.hackathon.filighbooking.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class TripModel implements Serializable{
    private Airport originAirport;
    private Airport destinationAirport;
    private Date departureDay;
    private Date returnDay;
    private Flight flightOutward;
    private Flight flightReturn;
    private boolean isReturnFlight;
    private int numOfPassenger;

    public TripModel(Airport originAirport, Airport destinationAirport , Date departureDay, Date returnDay, boolean isReturnFlight, int numOfPassenger) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
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

    public Airport getOriginPlaceID() {
        return originAirport;
    }

    public void setOriginPlaceID(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationPlaceID() {
        return destinationAirport;
    }

    public void setDestinationPlaceID(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
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
