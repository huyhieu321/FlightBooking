package com.hackathon.filighbooking.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.adapter.AirportAdapter.AirportViewHolder;

import java.util.ArrayList;

public class AirportAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    ArrayList<String> mListAirports;
    String mTitle;
    int isHeader =1;

    public AirportAdapter(ArrayList<String> pListAirports,String pTitle){
        this.mListAirports = pListAirports;
        this.mTitle = pTitle;
    }
    public static class AirportViewHolder extends ViewHolder {

        public AirportViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define
        }
    }
    public static  class HeaderAirportViewHolder extends  ViewHolder{

        public HeaderAirportViewHolder(@NonNull View itemView) {
            super(itemView);
            // Define
            TextView txtSearchTitle = itemView.findViewById(R.id.txtSearchTitle);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if(viewType == TYPE_ITEM){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search_place_dialog,viewGroup,false);
            return new AirportViewHolder(view);
        }
        if(viewType == TYPE_HEADER){
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_header_search_dialog,viewGroup,false);
            return new HeaderAirportViewHolder(view);
        }
        throw new RuntimeException("No match for " + viewType + ".");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // bind
        if (holder instanceof HeaderAirportViewHolder){

        }
        if(holder instanceof AirportViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        //return mListAirports.size();

        return 4 +isHeader;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {

        return position == 0;
    }
}
