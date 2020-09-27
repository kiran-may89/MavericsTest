package com.test.mavericstest.net

import com.google.gson.Gson

import com.test.mavericstest.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceConfig {

    private fun providesRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    }


    fun getApiCalls(): Api {

        return providesRetrofit().create(Api::class.java)
    }
}