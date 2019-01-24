package com.hackathon.filighbooking.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;

public class FlightDetailsActivity extends AppCompatActivity {


    public static void open(Activity activity, TripModel tripModel){
        Intent intent = new Intent(activity, FlightDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("flight",tripModel);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
    }
}
