package com.hackathon.filighbooking.getFlightList;

import android.content.Context;

import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;

import java.util.Date;
import java.util.List;

public class GetFlightPresenter implements GetFlightListener{
    private  GetFlightIterator mIterator;
    private GetFlightView mGetFlightView;
    private Context mContext;

    public GetFlightPresenter(GetFlightView mGetFlightView, Context pContext) {
        this.mGetFlightView = mGetFlightView;
        this.mContext = pContext;
        this.mIterator = new GetFlightIterator(mContext,this);
    }
    public void getFlightListForTrip(Date departureDate, String departureAirportCode, String arrivalAirportCode){
        // Iterator work
        mIterator.getListFlight(departureDate,departureAirportCode,arrivalAirportCode);
    }
    public void getFlightListForReturnTrip(Date departureDate, Date returnDate, String departureAirportCode, String arrivalAirportCode){
        // Iterator work
        mIterator.getListReturnFlight(departureDate,returnDate,departureAirportCode,arrivalAirportCode);
    }

    @Override
    public void getFlightForTripSuccess(List<Flight> flightList) {
        //TODO: Pass to view FlightsList
        mGetFlightView.getFlightForTripSuccess(flightList);
    }

    @Override
    public void getFlightForTripError(String message) {

    }

    @Override
    public void getFlightForReturnTripSuccess(List<Flight> outwardFlightsList, List<Flight> returnFlightsList) {
        // TODO: Pass to view FlightsList
        mGetFlightView.getFlightForReturnTripSuccess(outwardFlightsList,returnFlightsList);
    }


}
