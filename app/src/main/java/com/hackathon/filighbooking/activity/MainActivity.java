package com.hackathon.filighbooking.activity;

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

import com.hackathon.filighbooking.Dialog.SearchPlaceDialog;
import com.hackathon.filighbooking.model.entity.Flight;
import com.hackathon.filighbooking.model.entity.TripModel;
import com.hackathon.filighbooking.presenter.TripModelPresenter;
import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.APIUtils;
import com.hackathon.filighbooking.R;

import org.billthefarmer.view.CustomCalendarDialog;
import org.billthefarmer.view.CustomCalendarDialog.OnDateSetListener;
import org.billthefarmer.view.CustomCalendarView;
import org.billthefarmer.view.DayDecorator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements MainView,OnCheckedChangeListener,View.OnClickListener,OnDateSetListener {
    LinearLayout mDestinationDayLayout,mDestinationDayLayoutDisabled;
    CheckBox mReturnTripCheckBox;
    TextView txtOriginPlace,txtDestinationPlace, txtOriginDate, txtOriginMonth,txtOriginYear,
            txtDestinationDate,txtDestinationMonth,txtDestinationYear,
            txtDestinationDateDisable,txtDestinationMonthDisable,txtDestinationYearDisable;
    APIService mApiService;
    Button btnFindFlights;

    TripModel mTripModel = new TripModel();

    @RequiresApi(api = VERSION_CODES.LOLLIPOP)
    @Override


    protected void onCreate(Bundle savedInstanceState)  {
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
        CustomCalendarDialog dialog = new CustomCalendarDialog(this,this,2019,1,16);
        CustomCalendarView calendarView = dialog.getCalendarView();

        dialog.show();

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

        // Place box
        txtOriginPlace = findViewById(R.id.txtOriginPlace);
        txtDestinationPlace = findViewById(R.id.txtDestinationPlace);

        //Date box
        mDestinationDayLayout = findViewById(R.id.mDestinationDayLayout);
        mDestinationDayLayoutDisabled = findViewById(R.id.mDestinationDayLayoutDisabled);
        mReturnTripCheckBox = findViewById(R.id.mReturnTripCheckbox);

        txtOriginDate = findViewById(R.id.txtOriginDate);
        txtOriginMonth = findViewById(R.id.txtOriginMonth);
        txtOriginYear = findViewById(R.id.txtOriginYear);

        txtDestinationDate = findViewById(R.id.txtDestinationDay);
        txtDestinationMonth = findViewById(R.id.txtDestinationMonth);
        txtDestinationYear = findViewById(R.id.txtDestinationYear);

        txtDestinationDateDisable = findViewById(R.id.txtDestinationDayDisable);
        txtDestinationMonthDisable = findViewById(R.id.txtDestinationMonthDisable);
        txtDestinationYearDisable = findViewById(R.id.txtDestinationYearDisable);


        // set default check box true
        mReturnTripCheckBox.setChecked(true);

        // Button
        btnFindFlights = findViewById(R.id.btnFindFlights);

        // Set Listener
        mReturnTripCheckBox.setOnCheckedChangeListener(this);
        txtOriginPlace.setOnClickListener(this);
        txtDestinationPlace.setOnClickListener(this);
        mDestinationDayLayout.setOnClickListener(this);
        btnFindFlights.setOnClickListener(this);
    }
    public void initDateDefault(){
        // Get current time and set to origin date box
        Calendar c = Calendar.getInstance();
        txtOriginDate.setText(String.valueOf(c.get(Calendar.DAY_OF_MONTH)));
        txtOriginMonth.setText( getString(R.string.month) +" "+ (c.get(Calendar.MONTH)+1));
        String dayOfWeekOrigin = getDayOfWeekVietnamese(String.valueOf(c.get(Calendar.DAY_OF_WEEK)));
        txtOriginYear.setText(dayOfWeekOrigin + String.valueOf(c.get(Calendar.YEAR)));

        // Move to 3 days later, set to defaultDay at destination date box
        Calendar defaultDay = getDefaultReturnDay(c);

        txtDestinationDate.setText(String.valueOf(defaultDay.get(Calendar.DAY_OF_MONTH)));
        txtDestinationMonth.setText(getString(R.string.month) +" " + (defaultDay.get(Calendar.MONTH)+1));
        String dayOfWeekDestination = getDayOfWeekVietnamese(String.valueOf(defaultDay.get(Calendar.DAY_OF_WEEK)));
        txtDestinationYear.setText(dayOfWeekDestination + String.valueOf(defaultDay.get(Calendar.YEAR)));
        // Also set for disabled layout
        txtDestinationDateDisable.setText(String.valueOf(defaultDay.get(Calendar.DAY_OF_MONTH)));
        txtDestinationMonthDisable.setText(getString(R.string.month) +" " + (defaultDay.get(Calendar.MONTH)+1));
        txtDestinationYearDisable.setText(dayOfWeekDestination + String.valueOf(defaultDay.get(Calendar.YEAR)));

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(!isChecked){
            mDestinationDayLayout.setVisibility(View.GONE);
            mDestinationDayLayoutDisabled.setVisibility(View.VISIBLE);
        }
        else {
            mDestinationDayLayoutDisabled.setVisibility(View.GONE);
            mDestinationDayLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.equals(txtOriginPlace)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            SearchPlaceDialog searchPlaceDialog = new SearchPlaceDialog(getString(R.string.outward_leg));
            searchPlaceDialog.show(fragmentManager,null);
        }
        if(v.equals(txtDestinationPlace)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            SearchPlaceDialog searchPlaceDialog = new SearchPlaceDialog(getString(R.string.return_leg));
            searchPlaceDialog.show(fragmentManager,null);
        }
        if (v.equals(btnFindFlights)){
            if(mTripModel!=null){
                ChooseFlightActivity.open(MainActivity.this,mTripModel);
            }
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
    private Calendar getDefaultReturnDay(Calendar c){
        c.add(Calendar.DAY_OF_YEAR,3);
        Calendar defaultCalendar = c;
        return defaultCalendar;
    }

    @Override
    public void onDateSet(CustomCalendarView view, int year, int month, int date) {

    }
}
