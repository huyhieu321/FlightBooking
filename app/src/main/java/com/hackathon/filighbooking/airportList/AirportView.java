package com.hackathon.filighbooking.airportList;

import com.hackathon.filighbooking.model.entity.Airport;

import java.util.ArrayList;
import java.util.List;

public interface AirportView {
    void displayListDeparture(ArrayList<Airport> pListDeparture);

    void displayListArrival(ArrayList<Airport> pListArrival);
}
