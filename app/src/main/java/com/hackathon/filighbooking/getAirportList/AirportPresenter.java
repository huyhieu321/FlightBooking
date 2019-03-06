package com.hackathon.filighbooking.getAirportList;

import android.content.Context;

import com.hackathon.filighbooking.model.entity.Airport;

import java.util.ArrayList;

public class AirportPresenter implements AirportListener{
    private AirportIterator mIterator;
    private AirportView mAirportView;
    private Context mContext;
    public AirportPresenter(AirportView pAirportView, Context pContext){
        this.mContext = pContext;
        this.mIterator = new AirportIterator(this,mContext);
        this.mAirportView = pAirportView;
    }
    public void setAirportArrivalCode(String pAirportArrivalCode){
        mIterator.getListAirportRoute(pAirportArrivalCode);
    }
    public void getListAirport(){
        mIterator.getListAirport();
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
