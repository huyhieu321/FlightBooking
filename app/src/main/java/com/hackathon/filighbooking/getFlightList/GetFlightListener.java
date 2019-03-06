package com.hackathon.filighbooking.getFlightList;

import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

public interface GetFlightListener {
    void getFlightForTripSuccess(List<Flight> flightList);
    void getFlightForReturnTripSuccess(List<Flight> outwardFlightsList, List<Flight> returnFlightsList);
    void getFlightForTripError(String message);
}
