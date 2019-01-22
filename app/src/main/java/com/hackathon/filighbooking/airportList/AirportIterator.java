package com.hackathon.filighbooking.airportList;

import android.content.Context;

import com.hackathon.filighbooking.model.entity.Airport;
import com.hackathon.filighbooking.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AirportIterator {
    private AirportListener mAirportListener;
    ArrayList<Airport> mAllListAirport = new ArrayList<>();

    public AirportIterator(AirportListener pAirportListener) {
        this.mAirportListener = pAirportListener;
    }

    public void getListAirport() {
        ArrayList<Airport> mListDeparture = new ArrayList<>();
        // Doc Json tu airport list.json
        String jsonString = loadJsonFromAsset(Constant.getContext(),"airport_list.json");
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i< jsonArray.length(); i++){
                // Json object
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String airport = jsonObject.getString("airport");
                String type = jsonObject.getString("type");
                String code = jsonObject.getString("code");
                String name = jsonObject.getString("name");
                String normalizedName = jsonObject.getString("normalizedName");
                String shortName = jsonObject.getString("shortName");
                String category_name = jsonObject.getString("category_name");
                String timezone = jsonObject.getString("timezone");

                // Init to model
                Airport airportItem = new Airport();
                airportItem.setAirport(airport);
                airportItem.setType(type);
                airportItem.setCode(code);
                airportItem.setName(name);
                airportItem.setNormalizedName(normalizedName);
                airportItem.setShortName(shortName);
                airportItem.setCategoryName(category_name);
                airportItem.setTimezone(timezone);

                // Add to list
                mListDeparture.add(airportItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mAllListAirport = mListDeparture;

        /** Pass to presenter **/
        mAirportListener.getListAirportDepartureSuccess(mListDeparture);
    }
    public void getListAirportRoute(String pAirportCode){
        if (pAirportCode != null){
            ArrayList<Airport> mListArrival = new ArrayList<>();
            String jsonString = loadJsonFromAsset(Constant.getContext(),"domestic_routes.json");

            try {
                JSONObject jsonObject = new JSONObject(jsonString);

                JSONArray jsonArray = jsonObject.getJSONArray(pAirportCode);
                for (int i = 0; i < jsonArray.length() ; i++) {
                    // Get Json object
                    JSONObject item = jsonArray.getJSONObject(i);
                    String duration = item.getString("duration");
                    String position = item.getString("position");
                    String rank = item.getString("rank");
                    String code = item.getString("code");
                    String distance = item.getString("distance");

                    // Search on List Airport
                    for(Airport airport: mAllListAirport){
                        if (airport.getCode().equals(code)){
                            mListArrival.add(airport);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            /**Pass to presenter**/
            mAirportListener.getListAirportArrivalSuccess(mListArrival);
        }

    }


    public String loadJsonFromAsset(Context context,String fileName){
        String jsonString = null;

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte buffer[] = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer,"UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }
}