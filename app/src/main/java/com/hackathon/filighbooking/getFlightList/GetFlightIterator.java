package com.hackathon.filighbooking.getFlightList;

import android.content.Context;
import android.util.Log;

import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;
import com.hackathon.filighbooking.utils.Constant;

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
    public GetFlightIterator(Context pContext, GetFlightListener mGetFlightListener) {
        this.mGetFlightListener = mGetFlightListener;
        this.mContext = pContext;
        service = APIUtils.getAPIService(mContext);
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
                        mGetFlightListener.getFlightForTripSuccess(flights);
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
        String urlGetFlight1 = generateApiUrl(departureDate,departureAirportCode,arrivalAirportCode);
        String urlGetFlight2 = generateApiUrl(departureDate,arrivalAirportCode,departureAirportCode);
        Observable<List<Flight>> request1 = service.getFlight(Constant.API_AUTH,urlGetFlight1).subscribeOn(Schedulers.io());
        Observable<List<Flight>> request2 = service.getFlight(Constant.API_AUTH,urlGetFlight2).subscribeOn(Schedulers.io());
        Observable
                .merge(request1,request2)
                .ignoreElements()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(()->{
                    Log.i("Mes","Do success ");
                })
                .subscribe();
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
