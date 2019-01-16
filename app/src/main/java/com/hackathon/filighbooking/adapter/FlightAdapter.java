package com.hackathon.filighbooking.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.activity.FlightDetailsActivity;
import com.hackathon.filighbooking.activity.OnItemClickListener;
import com.hackathon.filighbooking.adapter.FlightAdapter.FlightViewHolder;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightViewHolder>  {
    List<Flight> mListFlights;
    Activity mActivity;
    public FlightAdapter(Activity pActivity,List<Flight> pListFlights) {
        this.mListFlights = pListFlights;
        this.mActivity = pActivity;
    }
    OnItemClickListener listener;
    public class FlightViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        TextView txtOriginID, txtDestinationID, txtArrivalTime, txtDepartureTime,txtFlightDuration,txtFlightPrice;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOriginID = itemView.findViewById(R.id.txtOriginID);
            txtDestinationID = itemView.findViewById(R.id.txtDestinationID);
            txtArrivalTime = itemView.findViewById(R.id.txtArrivalTime);
            txtDepartureTime = itemView.findViewById(R.id.txtDepartureTime);
            txtFlightDuration = itemView.findViewById(R.id.txtFlightDuration);
            txtFlightPrice = itemView.findViewById(R.id.txtFlightPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(itemView,getAdapterPosition());
        }
    }
    public void setClickListener(OnItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    @NonNull
    @Override

    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_flights_information,viewGroup,false);
        return new FlightViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder flightViewHolder, int position) {
        if (mListFlights.size()>0){
            Flight tempFlight = mListFlights.get(position);
            flightViewHolder.txtOriginID.setText(tempFlight.getOriginCode());
            flightViewHolder.txtDepartureTime.setText(formatTime(tempFlight.getDepartureTime()));
            flightViewHolder.txtDestinationID.setText(tempFlight.getDestinationCode());
            flightViewHolder.txtArrivalTime.setText(formatTime(tempFlight.getArrivalTime()));
            flightViewHolder.txtFlightPrice.setText(tempFlight.getCheapestPrice()+" VND/vé");
        }
    }

    @Override
    public int getItemCount() {
        if (mListFlights!=null) return mListFlights.size();
        else return 0;
    }

    private String formatTime(String time){
        return time.substring(11,16);
    }
}

