package com.hackathon.filighbooking.networking;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientAPI {
    private static Retrofit retrofit = null;
    private Context mContext;
    OkHttpClient okHttpClient;
    public ClientAPI(Context pContext) {
        this.mContext = pContext;
    }
    //get UnsafeOkHttpClient Builder to access not safe HTTP connect
    static OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClientBuilder();

    public Retrofit getClient (String baseUrl) {
        // Add Interceptor to check internet connection.
        okHttpClient = builder.addInterceptor(new NetworkConnectivityInterceptor(mContext) {
            @Override
            public boolean isInternetAvailable() {
                return false;
            }

            @Override
            public void onInternetUnavailable() {

            }
        }).build();
        if (retrofit==null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .client(okHttpClient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }
        return retrofit;
    }
}
