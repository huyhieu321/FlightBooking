package com.hackathon.filighbooking.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hackathon.filighbooking.fragment.FragmentChangeDay;
import com.hackathon.filighbooking.fragment.FragmentOutwardLeg;
import com.hackathon.filighbooking.fragment.FragmentReturnLeg;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

public class ChooseFlightTabHostAdapter extends FragmentPagerAdapter {
    boolean isReturnTrip = true ;
    List<Flight> outwardListFlight, returnLegListFlights;
    public ChooseFlightTabHostAdapter(FragmentManager fm, boolean isReturnTrip,
                                      List<Flight> outwardLegListFlight, List<Flight> returnLegListFlight) {
        super(fm);
        this.isReturnTrip = isReturnTrip;
        this.outwardListFlight = outwardLegListFlight;
        this.returnLegListFlights = returnLegListFlight;
    }

    public ChooseFlightTabHostAdapter(FragmentManager fm,List<Flight> outwardLegListFlight ) {
        super(fm);
        this.outwardListFlight = outwardLegListFlight;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (isReturnTrip) {
            switch (position) {
                case 0:
                    fragment = new FragmentOutwardLeg(outwardListFlight);
                    break;
                case 1:
                    fragment = new FragmentReturnLeg(returnLegListFlights);
                    break;
                case 2:
                    fragment = new FragmentChangeDay();
                    break;
            }
        }
        else {
            switch (position) {
                case 0:
                    fragment = new FragmentOutwardLeg(outwardListFlight);
                    break;
                case 1:
                    fragment = new FragmentChangeDay();
                    break;
            }
        }
        return fragment;
    }


    @Override
    public int getCount() {
        if (isReturnTrip) return 3;
        else return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        if (isReturnTrip){
            switch (position){
                case 0:
                    title = "Lượt đi";
                    break;
                case 1:
                    title = "Lượt về";
                    break;
                case 2:
                    title = "Đổi ngày";
                    break;
            }
        }
        else{
            switch (position){
                case 0:
                    title = "Lượt đi";
                    break;
                case 1:
                    title = "Đổi ngày";
                    break;
            }
        }
        return title;
    }
}
