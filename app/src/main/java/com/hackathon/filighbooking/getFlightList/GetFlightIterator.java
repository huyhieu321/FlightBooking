package com.hackathon.filighbooking.getFlightList;

import android.content.Context;
import android.util.Log;

import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;
import com.hackathon.filighbooking.utils.Constant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GetFlightIterator {
    private  GetFlightListener mGetFlightListener;
    private APIService service;
    private Context mContext;
    List<Flight> outwardLegFlightsList;
    List<Flight> returnLegFlightsList;
    boolean isGetReturnListSuccess;
    boolean isGetOutwardListSuccess;
    public GetFlightIterator(Context pContext, GetFlightListener mGetFlightListener) {
        this.mGetFlightListener = mGetFlightListener;
        this.mContext = pContext;
        service = APIUtils.getAPIService(mContext);
        outwardLegFlightsList = new ArrayList<>();
    }

    public  void getListFlight(Date departureDate,String departureAirportCode, String arrivalAirportCode){
        String urlGetFlight = generateApiUrl(departureDate, departureAirportCode, arrivalAirportCode);
        service.getFlight(Constant.API_AUTH,urlGetFlight)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(List<Flight> flights) {
                        // pass List Flight to presenter
                        outwardLegFlightsList = flights;
                        mGetFlightListener.getFlightForTripSuccess(outwardLegFlightsList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mGetFlightListener.getFlightForTripError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i("Mess","successs");
                    }
                });

    }
    public void getListReturnFlight(Date departureDate, Date returnDate,String departureAirportCode, String arrivalAirportCode){
        String urlGetFlightOutwardLeg = generateApiUrl(departureDate,departureAirportCode,arrivalAirportCode);
        // Change original Airport -> departure Airport for URL to get List Return Flights
        String urlGetFlightReturnLeg = generateApiUrl(returnDate,arrivalAirportCode,departureAirportCode);
        isGetOutwardListSuccess = false;
        isGetReturnListSuccess = false;
        returnLegFlightsList = new ArrayList<>();
        service.getFlight(Constant.API_AUTH,urlGetFlightOutwardLeg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(List<Flight> flightList) {
                        outwardLegFlightsList = flightList;
                        isGetOutwardListSuccess = true;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        service.getFlight(Constant.API_AUTH,urlGetFlightReturnLeg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<List<Flight>>() {
                    @Override
                    public void onNext(List<Flight> flightList) {
                            returnLegFlightsList = flightList;
                            isGetReturnListSuccess = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        if(isGetReturnListSuccess && isGetOutwardListSuccess){
                            mGetFlightListener.getFlightForReturnTripSuccess(outwardLegFlightsList,returnLegFlightsList);
                        }
                    }
                });

    }


    public String generateApiUrl(Date date, String originPlaceID, String destinationPlaceID){
        String dayArrival = String.valueOf(date.getYear()+1900) + formatDateMonth(date.getMonth()+1) + formatDateMonth(date.getDate());

        //TODO: pattern URL "/air/v1/search/TBB/HAN/20190117/101"
        return  "/air/v1/search/" + originPlaceID +"/" + destinationPlaceID +"/" + dayArrival +"/100/";
    }

    private String formatDateMonth(int number){
        //TODO: Add "0" if dateOfMonth < 10, to fulfill format of API URL.
        if (number<10){
            return "0"+String.valueOf(number);
        }
        else return String.valueOf(number);
    }
}
