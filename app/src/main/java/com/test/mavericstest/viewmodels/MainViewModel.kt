package com.test.mavericstest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mavericstest.models.MovieModel
import com.test.mavericstest.repos.MoviesRepo

class MainViewModel : ViewModel() {
    private val _movieMutableLiveData: MutableLiveData<MovieModel> = MutableLiveData()
    val movieLiveData: LiveData<MovieModel> get() = _movieMutableLiveData
    private val repos = MoviesRepo()
    init {
        search()
    }
    fun search(query: String = "Marvel") {
        repos.getMovies(_movieMutableLiveData, query)
    }
}