package com.hackathon.filighbooking.airportList;

import com.hackathon.filighbooking.model.entity.Airport;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.ArrayList;
import java.util.List;

public interface AirportListener {
    void getListAirportDepartureSuccess(ArrayList<Airport> pListDeparture);
    void getListAirportArrivalSuccess(ArrayList<Airport> pListArrival);
}
