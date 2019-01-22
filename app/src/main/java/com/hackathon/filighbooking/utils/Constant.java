package com.hackathon.filighbooking.utils;

import android.app.Application;
import android.content.Context;

public class Constant extends Application {
    public static String API_AUTH = "c39cf4bbb0b38d00ff985739153e05cd";
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
