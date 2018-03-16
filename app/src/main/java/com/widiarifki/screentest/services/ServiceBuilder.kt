package com.widiarifki.screentest.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by widiarifki on 08/03/2018.
 */

object ServiceBuilder {
    private val URL = "http://dry-sierra-6832.herokuapp.com/api/"

    // Create logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create okhttp client
    private val okHttpBuilder = OkHttpClient.Builder().addInterceptor(logger)

    private val builder = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpBuilder.build())

    private val retrofit = builder.build()

    fun <S> buildService(serviceType: Class<S>): S {
        return retrofit.create(serviceType)
    }
}
