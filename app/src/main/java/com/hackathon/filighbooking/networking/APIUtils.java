package com.hackathon.filighbooking.networking;

import com.hackathon.filighbooking.networking.APIService;
import com.hackathon.filighbooking.networking.ClientAPI;

public class APIUtils {
    private APIUtils(){}
    public static final String Base_url = "https://pubapi.atadi.vn/";
    public static APIService getAPIService(){
        return ClientAPI.getClient(Base_url).create(APIService.class);
    }
}


