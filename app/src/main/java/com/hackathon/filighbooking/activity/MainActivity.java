package com.hackathon.filighbooking.activity;

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

import com.hackathon.filighbooking.getAirportList.AirportPresenter;
import com.hackathon.filighbooking.getAirportList.AirportView;
import com.hackathon.filighbooking.dialog.SearchPlaceDialog;
import com.hackathon.filighbooking.dialog.SearchPlaceDialogListener;
import com.hackathon.filighbooking.model.entity.Airport;
import com.hackathon.filighbooking.model.entity.TripModel;
import com.hackathon.filighbooking.R;

import org.billthefarmer.view.CustomCalendarDialog.OnDateSetListener;
import org.billthefarmer.view.CustomCalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements AirportView,OnCheckedChangeListener,View.OnClickListener,OnDateSetListener {
    LinearLayout mReturnDayLayout, mReturnDayLayoutDisabled, mDepartureDayLayout;
    CheckBox mReturnTripCheckBox;
    TextView txtOriginPlace,txtDestinationPlace, txtOriginDate, txtOriginMonth,txtOriginYear,
            txtDestinationDate,txtDestinationMonth,txtDestinationYear,
            txtDestinationDateDisable,txtDestinationMonthDisable,txtDestinationYearDisable;
    Button btnFindFlights;
    TripModel mTripModel;
    AirportPresenter presenter;
    SearchPlaceDialog searchPlaceDialog,searchPlaceDialog1;

    boolean flag = false;
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
        initDateDefault();

        //Test
        Calendar calendar = Calendar.getInstance();
        Date departureDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH,2);
        Date returnDate = calendar.getTime();
        mTripModel = new TripModel();
        mTripModel.setDepartureDay(departureDate);
        mTripModel.setIsReturnFlight(true);
        mTripModel.setReturnDay(returnDate);
        /*********/
        presenter = new AirportPresenter(this,this);
        presenter.getListAirport();
//        CustomCalendarDialog dialog = new CustomCalendarDialog(this,this,2019,1,16);
////        CustomCalendarView calendarView = dialog.getCalendarView();
////
////        dialog.show();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(!isChecked){
            mReturnDayLayout.setVisibility(View.GONE);
            mReturnDayLayoutDisabled.setVisibility(View.VISIBLE);
            mTripModel.setIsReturnFlight(false);
        }
        else {
            mReturnDayLayoutDisabled.setVisibility(View.GONE);
            mReturnDayLayout.setVisibility(View.VISIBLE);
            mTripModel.setIsReturnFlight(true);
        }
    }

    @Override
    public void onClick(View v) {

        if(v.equals(txtOriginPlace)){
            FragmentManager fragmentManager = getSupportFragmentManager();
            searchPlaceDialog.show(fragmentManager,null);
            flag = true;
        }
        if(v.equals(txtDestinationPlace)){
//            if(flag == true){
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                searchPlaceDialog1.show(fragmentManager,null);
//            }
//            else{
//                Toast.makeText(this,"chon diem den",Toast.LENGTH_SHORT).show();
//            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            searchPlaceDialog1.show(fragmentManager,null);

        }
        if (v.equals(btnFindFlights)){
            if(mTripModel!=null){
                ChooseFlightActivity.open(MainActivity.this,mTripModel);
            }
        }
        if(v.equals(mReturnDayLayout)){

        }
        if (v.equals(mDepartureDayLayout)){

        }
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
        mDepartureDayLayout = findViewById(R.id.mDepartureDayLayout);
        mReturnDayLayout = findViewById(R.id.mReturnDayLayout);
        mReturnDayLayoutDisabled = findViewById(R.id.mReturnDayLayoutDisabled);
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
        mReturnDayLayout.setOnClickListener(this);
        mDepartureDayLayout.setOnClickListener(this);
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

    @Override
    public void displayListDeparture(ArrayList<Airport> pListDeparture) {
        txtOriginPlace.setText(pListDeparture.get(0).getName());
        mTripModel.setDepartureAirport(pListDeparture.get(0));
        presenter.setAirportArrivalCode(pListDeparture.get(0).getCode());
        searchPlaceDialog = new SearchPlaceDialog(getString(R.string.main_outward_leg), pListDeparture);
        searchPlaceDialog.setListener(new SearchPlaceDialogListener() {
            @Override
            public void getAirport(Airport airport) {
                Log.i("Result",airport.getName());
                mTripModel.setDepartureAirport(airport);
                txtOriginPlace.setText(airport.getName());
                presenter.setAirportArrivalCode(airport.getCode());
                searchPlaceDialog.dismiss();
            }
        });

    }

    @Override
    public void displayListArrival(ArrayList<Airport> pListArrival) {
        txtDestinationPlace.setText(pListArrival.get(0).getName());
        mTripModel.setArrivalAirport(pListArrival.get(0));
        searchPlaceDialog1 = new SearchPlaceDialog(getString(R.string.main_return_leg), pListArrival);
        searchPlaceDialog1.setListener(new SearchPlaceDialogListener() {
            @Override
            public void getAirport(Airport airport) {
                mTripModel.setArrivalAirport(airport);
                Log.i("Luot ve: ", airport.getName());
                txtDestinationPlace.setText(airport.getName());
                searchPlaceDialog1.dismiss();
            }
        });
    }


}
