package com.hackathon.filighbooking.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hackathon.filighbooking.fragment.FragmentChangeDay;
import com.hackathon.filighbooking.fragment.FragmentOutwardLeg;
import com.hackathon.filighbooking.fragment.FragmentOutwardLegListener;
import com.hackathon.filighbooking.fragment.FragmentReturnLeg;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

public class ChooseFlightTabHostAdapter extends FragmentPagerAdapter implements FragmentOutwardLegListener {
    boolean isReturnTrip = false ;
    List<Flight> outwardListFlight, returnLegListFlights;
    ChooseFlightTabHostListener mListener;
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

    public void setTabHostListener(ChooseFlightTabHostListener listener){
        this.mListener = listener;
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
                    fragment = new FragmentOutwardLeg(returnLegListFlights); // Show returnLeg
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
                    ((FragmentOutwardLeg)fragment).setFragmentOutwardLegListener(this);
                    mListener.showNameFragment("alo alo");
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

    @Override
    public void getFLight(Flight flight) {
        mListener.getFlightDetails(flight);
    }
}
