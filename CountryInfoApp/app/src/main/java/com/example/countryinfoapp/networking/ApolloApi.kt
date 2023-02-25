package com.example.countryinfoapp.networking

import android.content.Context
import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.countryinfoapp.R
import com.example.countryinfoapp.constants.Constants.APOLLO_ENDPOINT_LINK
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class ApolloApi {
    fun getInstance(context: Context): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            context.getString(R.string.looper_message)
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return ApolloClient.Builder()
            .serverUrl(APOLLO_ENDPOINT_LINK)
            .okHttpClient(okHttpClient)
            .build()
    }
}