package com.hackathon.filighbooking.getAirportList;

import com.hackathon.filighbooking.model.entity.Airport;

import java.util.ArrayList;

public interface AirportListener {
    void getListAirportDepartureSuccess(ArrayList<Airport> pListDeparture);
    void getListAirportArrivalSuccess(ArrayList<Airport> pListArrival);
}
