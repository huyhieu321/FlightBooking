package com.hackathon.filighbooking.networking;

import com.google.gson.JsonObject;
import com.hackathon.filighbooking.model.entity.Flight;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

public interface APIService {
    @GET
    Observable<List<Flight>> getFlight(@Header("Authorization") String token, @Url String url);
}
