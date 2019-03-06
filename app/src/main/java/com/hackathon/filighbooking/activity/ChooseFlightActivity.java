package com.hackathon.filighbooking.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.ChooseFlightTabHostAdapter;
import com.hackathon.filighbooking.adapter.ChooseFlightTabHostListener;
import com.hackathon.filighbooking.getFlightList.GetFlightPresenter;
import com.hackathon.filighbooking.getFlightList.GetFlightView;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;
import java.util.Date;
import java.util.List;

public class ChooseFlightActivity extends AppCompatActivity implements ChooseFlightTabHostListener, GetFlightView {

    ViewPager viewPager;
    TabLayout tabLayout;
    boolean isReturnTrip;
    ChooseFlightTabHostAdapter chooseFlightTabHostAdapter;
    Date departureDate, returnDate;
    String arrivalAirportCode, departureAirportCode;
    Integer numOfPassenger;
    FragmentManager fragmentManager;
    TripModel mTripModel;
    TextView txtToolbarDepartureCode, txtToolbarArrivalCode;
    GetFlightPresenter mPresenter;
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

        // Bind data from Intent
        Intent intent = getIntent();
        mTripModel = (TripModel) intent.getSerializableExtra("trip_model");
        mPresenter = new GetFlightPresenter(this,this);

        isReturnTrip = mTripModel.isReturnFlight();
        departureAirportCode = mTripModel.getDepartureAirport().getCode();
        arrivalAirportCode = mTripModel.getArrivalAirport().getCode();
        departureDate = mTripModel.getDepartureDay();
        numOfPassenger = mTripModel.getNumOfPassenger();
        viewPager = findViewById(R.id.viewPagerChooseFlight);
        tabLayout = findViewById(R.id.tabLayoutChooseFlight);

        initView();
        if (!isReturnTrip){
            mPresenter.getFlightListForTrip(departureDate,departureAirportCode,arrivalAirportCode);
        }else {
            returnDate = mTripModel.getReturnDay();
            mPresenter.getFlightListForReturnTrip(departureDate,returnDate,departureAirportCode,arrivalAirportCode);
        }


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

    public void initView(){
        txtToolbarDepartureCode = findViewById(R.id.txtToolbarDeparturePlaceCode);
        txtToolbarDepartureCode.setText(mTripModel.getDepartureAirport().getCode());

        txtToolbarArrivalCode = findViewById(R.id.txtToolbarArrivalPlaceCode);
        txtToolbarArrivalCode.setText(mTripModel.getArrivalAirport().getCode());
        fragmentManager = getSupportFragmentManager();
    }



    private void initFlightsFragmentForTrip(List<Flight> list) {
        chooseFlightTabHostAdapter = new ChooseFlightTabHostAdapter(fragmentManager,list);
        chooseFlightTabHostAdapter.setTabHostListener(this);
        viewPager.setAdapter(chooseFlightTabHostAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(chooseFlightTabHostAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    private void initFlightsFragmentForReturnTrip(List<Flight> outwardLegListFlight, List<Flight> returnLegListFlight){
        chooseFlightTabHostAdapter = new ChooseFlightTabHostAdapter(fragmentManager,isReturnTrip,outwardLegListFlight,returnLegListFlight);
    }


    @Override
    public void getFlightDetails(Flight flight) {

//        FlightDetailsActivity.open(this,flight);
    }

    @Override
    public void showNameFragment(String data) {
        Log.i("Message",data);
    }


    @Override
    public void getFlightForTripSuccess(List<Flight> flightList) {
        initFlightsFragmentForTrip(flightList);
    }

    @Override
    public void getFlightForReturnTripSuccess(List<Flight> outwardFlightsList, List<Flight> returnFlightsList) {
        initFlightsFragmentForReturnTrip(outwardFlightsList,returnFlightsList);
    }
}
