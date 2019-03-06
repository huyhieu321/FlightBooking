package com.hackathon.filighbooking.networking

import android.content.Context
import android.widget.Toast
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

abstract class NetworkConnectivityInterceptor(context : Context) : Interceptor {
    private val mContext : Context
    init {
        mContext = context
    }

    abstract fun isInternetAvailable(): Boolean

    abstract fun onInternetUnavailable()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain):Response {
        if (!APIUtils.checkInternetConnection(mContext))
        {
            throw NoConnectivityException()
            onInternetUnavailable()
        }
        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }
}