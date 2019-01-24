package com.hackathon.filighbooking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.ChooseFlightTabHostAdapter;
import com.hackathon.filighbooking.adapter.ChooseFlightTabHostListener;
import com.hackathon.filighbooking.fragment.FragmentOutwardLegListener;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;
import com.hackathon.filighbooking.utils.Constant;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseFlightActivity extends AppCompatActivity implements ChooseFlightTabHostListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    boolean isReturnTrip;
    List<Flight> outwardLegListFlight, returnLegListFlight;
    ChooseFlightTabHostAdapter chooseFlightTabHostAdapter;
    Date departureDate, returnDate;
    String arrivalAirportCode, departureAirportCode;
    int numOfPassenger;
    APIService service;
    FragmentManager fragmentManager;
    public static void open(Activity activity, TripModel pTripModel){
        Intent intent = new Intent(activity,ChooseFlightActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("trip_model",pTripModel);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight);
        initToolbar();
        fragmentManager = getSupportFragmentManager();
        outwardLegListFlight = new ArrayList<>();

        service = APIUtils.getAPIService();

        // Bind data from Intent
        Intent intent = getIntent();
        TripModel mTripModel = (TripModel) intent.getSerializableExtra("trip_model");
        isReturnTrip = mTripModel.isReturnFlight();
        departureAirportCode = mTripModel.getDepartureAirport().getCode();
        arrivalAirportCode = mTripModel.getArrivalAirport().getCode();
        departureDate = mTripModel.getDepartureDay();
        returnDate = mTripModel.getReturnDay();
        numOfPassenger = mTripModel.getNumOfPassenger();
        viewPager = findViewById(R.id.viewPagerChooseFlight);
        tabLayout = findViewById(R.id.tabLayoutChooseFlight);

        new ParseOutwardFlights().execute();
        //Turn on dialog process



    }
    public void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_white);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public String generateUrlFindFlight(Date date, String originPlaceID, String destinationPlaceID){
        String url;

        String dayArrival = String.valueOf(date.getYear()+1900) + formatDateMonth(date.getMonth()+1) + formatDateMonth(date.getDate());

        // pattern URL "/air/v1/search/TBB/HAN/20190117/101"
        url = "/air/v1/search/" + originPlaceID +"/" + destinationPlaceID +"/" + dayArrival +"/100/";
        return url;
    }

    private void initView(List<Flight> list) {
        chooseFlightTabHostAdapter = new ChooseFlightTabHostAdapter(fragmentManager,list);
        chooseFlightTabHostAdapter.setTabHostListener(this);
        viewPager.setAdapter(chooseFlightTabHostAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(chooseFlightTabHostAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
    private String formatDateMonth(int number){
        if (number<10){
            return "0"+String.valueOf(number);
        }
        else return String.valueOf(number);
    }


    @Override
    public void getFlightDetails(Flight flight) {

//        FlightDetailsActivity.open(this,flight);
    }

    private class ParseOutwardFlights extends AsyncTask<Void,Void,Void>{
        List<Flight> listOutwardFlights = new ArrayList<>();
        @Override
        protected Void doInBackground(Void... params) {
            String urlOutwardFlights = generateUrlFindFlight(departureDate, departureAirportCode, arrivalAirportCode);
            service.getFlight(Constant.API_AUTH,urlOutwardFlights).enqueue(new Callback<List<Flight>>(){
                @Override
                public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
                    // turn off dialog process
                    if(response.isSuccessful()){
                        List<Flight> listFlights = response.body();
                        for(Flight flight : listFlights){
                            listOutwardFlights.add(flight);
                        }
                        initView(listOutwardFlights);
                    }else {
                        Toast.makeText(ChooseFlightActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Flight>> call, Throwable t) {

                }
            });
            return null;
        }
    }

}
