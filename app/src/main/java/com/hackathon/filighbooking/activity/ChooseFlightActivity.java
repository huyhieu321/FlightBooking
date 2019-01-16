package com.hackathon.filighbooking.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.ChooseFlightTabHostAdapter;
import com.hackathon.filighbooking.adapter.FlightAdapter;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseFlightActivity extends AppCompatActivity implements OnItemClickListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    boolean isReturnTrip;
    List<Flight> outwardLegListFlight, returnLegListFlight;
    ChooseFlightTabHostAdapter chooseFlightTabHostAdapter;
    public static void open(Activity activity, TripModel pTripModel){
        Intent intent = new Intent(activity,ChooseFlightActivity.class);
        intent.putExtra("trip_model",pTripModel);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight);
        initToolbar();
        //        mListFlights = new ArrayList<>();
        // init Fragment Tablayout
        Intent intent = getIntent();
        TripModel mTripModel = (TripModel) intent.getSerializableExtra("trip_model");
        isReturnTrip = mTripModel.isReturnFlight();
        viewPager = findViewById(R.id.viewPagerChooseFlight);
        tabLayout = findViewById(R.id.tabLayoutChooseFlight);
        FragmentManager fr = getSupportFragmentManager();
        if(isReturnTrip){
             chooseFlightTabHostAdapter  = new ChooseFlightTabHostAdapter(fr,isReturnTrip,outwardLegListFlight,returnLegListFlight);
        }
        else {
             chooseFlightTabHostAdapter = new ChooseFlightTabHostAdapter(fr,outwardLegListFlight);
        }

        viewPager.setAdapter(chooseFlightTabHostAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(chooseFlightTabHostAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//        View root = tabLayout.getChildAt(0);
//        if (root instanceof LinearLayout) {
//            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//            GradientDrawable drawable = new GradientDrawable();
//            drawable.setColor(getResources().getColor(R.color.white));
//            drawable.setSize(2, 1);
//            ((LinearLayout) root).setDividerPadding(10);
//            ((LinearLayout) root).setDividerDrawable(drawable);
//        }

        //Turn on dialog process
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.DAY_OF_YEAR,2);

        Date time= currentTime.getTime();
        APIService service = APIUtils.getAPIService();
        String url = generateUrlFindFlight(time,"TBB","HAN");

//        service.getFlight("c39cf4bbb0b38d00ff985739153e05cd",url).enqueue(new Callback<List<Flight>>(){
//            @Override
//            public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
//                // turn off dialog process
//                if(response.isSuccessful()){
//                    List<Flight> listFlights = response.body();
//                    for(Flight flight : listFlights){
//                        mListFlights.add(flight);
//                        flightAdapter.notifyDataSetChanged();
//                    }
//                }else {
//                    Toast.makeText(ChooseFlightActivity.this,response.message(),Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Flight>> call, Throwable t) {
//
//            }
//        });

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

        String dayArrival = String.valueOf(date.getYear()+1900) + formartDateMonth(date.getMonth()+1) + formartDateMonth(date.getDate());

        // pattern URL "/air/v1/search/TBB/HAN/20190117/101"
        url = "/air/v1/search/" + originPlaceID +"/" + destinationPlaceID +"/" + dayArrival +"/100/" + "";
        return url;
    }

    private String formartDateMonth(int number){
        if (number<10){
            return "0"+String.valueOf(number);
        }
        else return String.valueOf(number);
    }


    // On Item Click
    @Override
    public void onItemClick(View view, int position) {
//        FlightDetailsActivity.open(ChooseFlightActivity.this,mListFlights.get(position));
    }
}
