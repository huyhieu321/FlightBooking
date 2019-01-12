package com.hackathon.filighbooking.Dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.AirportAdapter;

@SuppressLint("ValidFragment")
public class SearchPlaceDialog extends DialogFragment{
    RecyclerView recyclerAirports;
    String mTitle;
    public SearchPlaceDialog(String pTitle){
        this.mTitle = pTitle;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(mTitle);
        return inflater.inflate(R.layout.search_place_dialog,container);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerAirports = view.findViewById(R.id.recyclerAirportList);
        AirportAdapter airportAdapter = new AirportAdapter(null,mTitle);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerAirports.setLayoutManager(linearLayoutManager);
        recyclerAirports.setAdapter(airportAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = LayoutParams.MATCH_PARENT;
            int height = LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
