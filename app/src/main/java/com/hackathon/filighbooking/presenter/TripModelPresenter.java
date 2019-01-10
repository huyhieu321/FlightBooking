package com.hackathon.filighbooking.presenter;

import com.hackathon.filighbooking.activity.MainView;
import com.hackathon.filighbooking.model.TripModelInterator;
import com.hackathon.filighbooking.model.TripModelListener;
import com.hackathon.filighbooking.model.entity.Flight;

public class TripModelPresenter implements TripModelListener{
    private TripModelInterator modelInterator;
    private MainView mainView;

    public TripModelPresenter(MainView mainView) {
        this.modelInterator = new TripModelInterator(this);
        this.mainView = mainView;
    }
    public void setFlight(String originCode, String destinationCode){
        modelInterator.createFlight(originCode,destinationCode);
    }
    @Override
    public void onGetFlightSuccess(Flight flight) {
        mainView.displayFlight(flight);
    }
}
