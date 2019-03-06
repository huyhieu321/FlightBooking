package com.hackathon.filighbooking.networking;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.ClientAPI;

import java.util.logging.Logger;

public class APIUtils {

    private APIUtils(){}
    public static final String Base_url = "https://pubapi.atadi.vn/";

    public static boolean checkInternetConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    public static APIService getAPIService(Context pContext){
        //Create a new clientAPI to pass Context for used
        ClientAPI clientAPI = new ClientAPI(pContext);
        return clientAPI.getClient(Base_url).create(APIService.class);
    }
}


