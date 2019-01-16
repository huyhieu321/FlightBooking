package com.hackathon.filighbooking.model;

import com.hackathon.filighbooking.model.entity.Flight;

public interface TripModelListener {
    void onGetFlightSuccess(Flight flight);

}
