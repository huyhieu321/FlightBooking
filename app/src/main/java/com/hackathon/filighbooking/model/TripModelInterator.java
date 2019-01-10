package com.hackathon.filighbooking.model;

import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;

public class TripModelInterator {
    private TripModelListener tripModelListener;
    public TripModelInterator(TripModelListener listener){
        this.tripModelListener = listener;
    }
    public void createFlight(String originCode,String destinationCode){
        Flight flight = new Flight();
        flight.setOriginCode(originCode);
        flight.setDestinationCode(destinationCode);
        tripModelListener.onGetFlightSuccess(flight);
    }

    public void changeDate(String newDay,Flight flight){

    }
}
