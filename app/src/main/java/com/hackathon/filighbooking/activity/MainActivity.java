package com.hackathon.filighbooking.activity;

import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hackathon.filighbooking.Dialog.SearchPlaceDialog;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.presenter.TripModelPresenter;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;
import com.hackathon.filighbooking.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements MainView,OnCheckedChangeListener,View.OnClickListener {
    LinearLayout mDestinationDayLayout,mDestinationDayLayoutDisabled;
    CheckBox mReturnTripCheckBox;
    TextView txtOriginPlace,txtDestinationPlace, txtOriginDate, txtOriginMonth,txtOriginYear,
            txtDestinationDay,txtDestinationMonth,txtDestinationYear;
    APIService mApiService;
    Button btnFindFlights;
    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
        initDateDefault();
        mApiService = APIUtils.getAPIService();
        final TripModelPresenter presenter = new TripModelPresenter(this);
//        View decorView = getWindow().getDecorView();
//        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void displayFlight(Flight flight) {
        Log.i("Result",flight.getOriginCode() + " " + flight.getDestinationCode());
    }
    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.toolbar_title_step1);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_black);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void initView() {
        // Find View
        mDestinationDayLayout = findViewById(R.id.mDestinationDayLayout);
        mDestinationDayLayoutDisabled = findViewById(R.id.mDestinationDayLayoutDisabled);
        mReturnTripCheckBox = findViewById(R.id.mReturnTripCheckbox);
        txtOriginPlace = findViewById(R.id.txtOriginPlace);
        txtDestinationPlace = findViewById(R.id.txtDestinationPlace);
        btnFindFlights = findViewById(R.id.btnFindFlights);
        txtOriginDate = findViewById(R.id.txtOriginDate);
        txtOriginMonth = findViewById(R.id.txtOriginMonth);
        txtOriginYear = findViewById(R.id.txtOriginYear);
        txtDestinationDay = findViewById(R.id.txtDestinationDay);
        txtDestinationMonth = findViewById(R.id.txtDestinationMonth);
        txtDestinationYear = findViewById(R.id.txtDestinationYear);
        // set default check box true
        mReturnTripCheckBox.setChecked(true);

        // Set Listener
        mReturnTripCheckBox.setOnCheckedChangeListener(this);
        txtOriginPlace.setOnClickListener(this);
        txtDestinationPlace.setOnClickListener(this);
        btnFindFlights.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChooseFlightActivity.class);
                startActivity(intent);
            }
        });
        mDestinationDayLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Calendar",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void initDateDefault(){
        //Date currentTime = new Date(System.currentTimeMillis());
        Calendar c = Calendar.getInstance();
        txtOriginDate.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        txtOriginMonth.setText("Tháng "+ (c.get(Calendar.MONTH)+1));
        String dayOfWeek = getDayOfWeekVietnamese(String.valueOf(c.get(Calendar.DAY_OF_WEEK)));
        txtOriginYear.setText(dayOfWeek + String.valueOf(c.get(Calendar.YEAR)));


    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(!isChecked){
//            for (int i = 0; i < mDestinationDayLayout.getChildCount(); i++) {
//                View child = mDestinationDayLayout.getChildAt(i);
//                child.setEnabled(false);
//            }
            mDestinationDayLayout.setVisibility(View.GONE);
            mDestinationDayLayoutDisabled.setVisibility(View.VISIBLE);
        }
        else {
//            for (int i = 0; i < mDestinationDayLayout.getChildCount(); i++) {
//                View child = mDestinationDayLayout.getChildAt(i);
//                child.setEnabled(true);
//            }
            mDestinationDayLayoutDisabled.setVisibility(View.GONE);
            mDestinationDayLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        TextView textView = (TextView) v;
        if(textView.equals(txtOriginPlace)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            SearchPlaceDialog searchPlaceDialog = new SearchPlaceDialog(getString(R.string.outward_leg));
            searchPlaceDialog.show(fragmentManager,null);
        }
        if(textView.equals(txtDestinationPlace)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            SearchPlaceDialog searchPlaceDialog = new SearchPlaceDialog(getString(R.string.return_leg));
            searchPlaceDialog.show(fragmentManager,null);
        }
    }

    private String getDayOfWeekVietnamese(String dayOfWeek){

        switch (dayOfWeek){
            case "2": return "Thứ 2, ";
            case "3": return "Thứ 3, ";
            case "4": return "Thứ 4, ";
            case "5": return "Thứ 5, ";
            case "6": return "Thứ 6, ";
            case "7": return "Thứ 7, ";
            case "1": return "Chủ nhật, ";
            default: return null;
        }
    }
}
