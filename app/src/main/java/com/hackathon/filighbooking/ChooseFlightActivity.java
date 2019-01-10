package com.hackathon.filighbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    }
}
