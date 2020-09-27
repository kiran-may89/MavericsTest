package com.test.mavericstest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mavericstest.models.AboutMovieModel
import com.test.mavericstest.models.MovieModel
import com.test.mavericstest.repos.MoviesRepo

class AboutMovieViewModel: ViewModel() {
    private val _aboutMovieMutableLiveData: MutableLiveData<AboutMovieModel> = MutableLiveData()
    val aboutMovieLiveData: LiveData<AboutMovieModel> get() = _aboutMovieMutableLiveData
    private val repos = MoviesRepo()

    fun getMovie(query: String ) {
        repos.aboutMovie(_aboutMovieMutableLiveData,query)
    }
}