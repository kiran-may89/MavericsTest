package com.test.mavericstest.repos

import androidx.lifecycle.MutableLiveData
import com.test.mavericstest.models.AboutMovieModel
import com.test.mavericstest.models.MovieModel
import com.test.mavericstest.net.ServiceConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepo {
    private val api  = ServiceConfig.getApiCalls()

     fun getMovies(liveData: MutableLiveData<MovieModel>, query:String){
        val call = api.getMovieList(query)
        call.enqueue(object:Callback<MovieModel>{
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
               if(response.isSuccessful){
                   liveData.value = response.body()!!
               }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {

            }

        })

    }
    fun aboutMovie(liveData: MutableLiveData<AboutMovieModel>, query:String){
        val call = api.aboutMovie(query)
        call.enqueue(object:Callback<AboutMovieModel>{
            override fun onResponse(call: Call<AboutMovieModel>, response: Response<AboutMovieModel>) {
                if(response.isSuccessful){
                    liveData.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<AboutMovieModel>, t: Throwable) {

            }

        })

    }
}