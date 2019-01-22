package com.hackathon.filighbooking.airportList;

import com.hackathon.filighbooking.model.entity.Airport;

import java.util.ArrayList;
import java.util.List;

public class AirportPresenter implements AirportListener{
    private AirportIterator mInterator;
    private AirportView mAirportView;

    public AirportPresenter(AirportView pAirportView){
        this.mInterator = new AirportIterator(this);
        this.mAirportView = pAirportView;
    }
    public void setAirportArrivalCode(String pAirportArrivalCode){
        mInterator.getListAirportRoute(pAirportArrivalCode);
    }
    public void getListAirtport(){
        mInterator.getListAirport();
    }
    @Override
    public void getListAirportDepartureSuccess(ArrayList<Airport> pListDeparture) {
        mAirportView.displayListDeparture(pListDeparture);
    }

    @Override
    public void getListAirportArrivalSuccess(ArrayList<Airport> pListArrival) {
        mAirportView.displayListArrival(pListArrival);
    }


}
