package com.hackathon.filighbooking.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.ChooseFlightAdapterClickListener;
import com.hackathon.filighbooking.adapter.ChooseFlightAdapter;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentOutwardLeg extends android.support.v4.app.Fragment implements ChooseFlightAdapterClickListener {
    RecyclerView recyclerListFlight;
    ChooseFlightAdapter flightAdapter;
    List<Flight> mListFlights = null;
    FragmentOutwardLegListener fragmentOutwardLegListener;
    public FragmentOutwardLeg(List<Flight> pListFlights) {
        this.mListFlights = pListFlights;
    }
    public void setFragmentOutwardLegListener(FragmentOutwardLegListener listener){
        this.fragmentOutwardLegListener = listener;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_outward_leg,container,false);
        recyclerListFlight = view.findViewById(R.id.recyclerFlightList);

        flightAdapter = new ChooseFlightAdapter(getActivity(),mListFlights);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerListFlight.setLayoutManager(layoutManager);
        recyclerListFlight.setAdapter(flightAdapter);
        flightAdapter.setClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(Flight flight) {
        fragmentOutwardLegListener.getFLight(flight);
    }
}
