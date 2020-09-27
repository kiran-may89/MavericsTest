package com.test.mavericstest.net

import com.google.gson.internal.GsonBuildConfig
import com.test.mavericstest.BuildConfig
import com.test.mavericstest.models.AboutMovieModel
import com.test.mavericstest.models.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {


    @GET(".")
    fun getMovieList(
        @Query("s") movie: String,
        @Query("type") type: String = "movie",
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): Call<MovieModel>
    @GET(".")
    fun aboutMovie(@Query("i") i: String, @Query("apikey") apiKey: String = BuildConfig.API_KEY):Call<AboutMovieModel>
}