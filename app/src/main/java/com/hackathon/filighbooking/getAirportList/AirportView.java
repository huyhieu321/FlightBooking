package com.hackathon.filighbooking.getAirportList;

import com.hackathon.filighbooking.model.entity.Airport;

import java.util.ArrayList;

public interface AirportView {
    void displayListDeparture(ArrayList<Airport> pListDeparture);

    void displayListArrival(ArrayList<Airport> pListArrival);
}
