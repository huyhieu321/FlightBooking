package com.hackathon.filighbooking.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hackathon.filighbooking.R;
import com.hackathon.filighbooking.model.entity.Airport;

import java.util.ArrayList;

public class SearchPlaceDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    ArrayList<Airport> mListAirports;
    String mTitle;
    int isHeader =1;
    SearchPlaceDialogAdapterClickListener mSearchDialogOnItemClick;

    public SearchPlaceDialogAdapter(ArrayList<Airport> pListAirports, String pTitle){
        this.mListAirports = pListAirports;
        this.mTitle = pTitle;
    }

    public  void setOnItemClickListener(SearchPlaceDialogAdapterClickListener pSearchDialogOnItemClick){
        this.mSearchDialogOnItemClick = pSearchDialogOnItemClick;
    }

    public static class AirportViewHolder extends ViewHolder  {
        TextView txtPlaceName;
        public AirportViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define
            txtPlaceName = itemView.findViewById(R.id.txtPlaceName);

        }
    }
    public static  class HeaderAirportViewHolder extends  ViewHolder{
        TextView txtSearchTitle;
        public HeaderAirportViewHolder(@NonNull View itemView) {
            super(itemView);
            // Define
           txtSearchTitle  = itemView.findViewById(R.id.txtSearchTitle);
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
            ((HeaderAirportViewHolder) holder).txtSearchTitle.setText(mTitle);
        }
        if(holder instanceof AirportViewHolder){
            String placeName = mListAirports.get(position-isHeader).getName();
            ((AirportViewHolder) holder).txtPlaceName.setText(placeName);
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( mSearchDialogOnItemClick !=null){
                        mSearchDialogOnItemClick.onClickItemListener(mListAirports.get(position-isHeader));
                        Log.i("ItemCLick",mListAirports.get(position-isHeader).getName());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListAirports.size() +isHeader;
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
