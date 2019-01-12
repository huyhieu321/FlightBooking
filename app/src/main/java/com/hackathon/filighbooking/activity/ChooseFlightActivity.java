package com.hackathon.filighbooking.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;

import com.hackathon.filighbooking.Dialog.SearchPlaceDialog;
import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.FlightAdapter;

public class ChooseFlightActivity extends AppCompatActivity {
    RecyclerView recyclerListFlight;
    FlightAdapter flightAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight);
        recyclerListFlight = findViewById(R.id.recyclerListFlight);
        flightAdapter = new FlightAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerListFlight.setAdapter(flightAdapter);
        recyclerListFlight.setLayoutManager(layoutManager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_white);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });

    }
}
