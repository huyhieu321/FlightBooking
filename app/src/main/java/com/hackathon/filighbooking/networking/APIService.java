package com.hackathon.filighbooking.networking;

import com.google.gson.JsonObject;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIService {
    @GET
    Call<List<Flight>> getFlight(@Header("Authorization") String token, @Url String url);
}
