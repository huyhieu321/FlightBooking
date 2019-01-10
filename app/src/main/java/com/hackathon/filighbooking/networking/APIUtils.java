package com.hackathon.filighbooking.networking;

import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.ClientAPI;

public class APIUtils {
    private APIUtils(){}
    public static final String Base_url = "http://pubapi.atadi.vn/air/v1/";
    public static APIService getAPIService(){
        return ClientAPI.getClient(Base_url).create(APIService.class);
    }
}


