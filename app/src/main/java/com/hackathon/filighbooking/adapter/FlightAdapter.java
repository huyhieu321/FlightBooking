package com.hackathon.filighbooking.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.FlightAdapter.FlightViewHolder;

public class FlightAdapter extends RecyclerView.Adapter<FlightViewHolder> {
    public FlightAdapter() {

    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder{

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_flights_information,viewGroup,false);
        return new FlightViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder flightViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
