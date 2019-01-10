package com.hackathon.filighbooking.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;

import com.hackathon.filighbooking.ChooseFlightActivity;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.presenter.TripModelPresenter;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;
import com.hackathon.filighbooking.R;


public class MainActivity extends AppCompatActivity implements MainView {
    LinearLayout mDestinationDayLayout;
    CheckBox mReturnTripCheckBox;
    APIService mApiService;
    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        mDestinationDayLayout = findViewById(R.id.mDestinationDayLayout);
        mReturnTripCheckBox = findViewById(R.id.mReturnTripCheckbox);
        mReturnTripCheckBox.setChecked(true);
        mApiService = APIUtils.getAPIService();
        final TripModelPresenter presenter = new TripModelPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("HUY");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_black);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseFlightActivity.class);
                startActivity(intent);
            }
        });
        mReturnTripCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    for (int i = 0; i < mDestinationDayLayout.getChildCount(); i++) {
                        View child = mDestinationDayLayout.getChildAt(i);
                        child.setEnabled(false);
                    }
                }
                else {
                    for (int i = 0; i < mDestinationDayLayout.getChildCount(); i++) {
                        View child = mDestinationDayLayout.getChildAt(i);
                        child.setEnabled(true);
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void displayFlight(Flight flight) {
        Log.i("Result",flight.getOriginCode() + " " + flight.getDestinationCode());
    }
}
