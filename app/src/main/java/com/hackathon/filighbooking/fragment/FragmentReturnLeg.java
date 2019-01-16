package com.hackathon.filighbooking.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentReturnLeg extends android.support.v4.app.Fragment {
    List<Flight> returnLegListFlights;
    public FragmentReturnLeg(List<Flight> flightList) {
        this.returnLegListFlights = flightList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_return_leg,container,false);
    }
}
