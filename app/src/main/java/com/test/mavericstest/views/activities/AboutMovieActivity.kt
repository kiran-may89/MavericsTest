package com.test.mavericstest.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.test.mavericstest.R
import com.test.mavericstest.models.AboutMovieModel
import com.test.mavericstest.viewmodels.AboutMovieViewModel
import com.test.mavericstest.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_about_movie.*
import kotlinx.android.synthetic.main.activity_about_movie.view.*

const val MOVIE_ID = "MOVIE_ID"

class AboutMovieActivity : AppCompatActivity() {
    private val viewmodel: AboutMovieViewModel by lazy {

        ViewModelProvider(this).get(AboutMovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_movie)
        initObserver()
    }

    private fun initObserver() {

        viewmodel.aboutMovieLiveData.observe(this) { movie ->
            bindData(movie)
        }
        intent.extras?.getString(MOVIE_ID)?.let { viewmodel.getMovie(it) }
    }


    private fun bindData(movie: AboutMovieModel) {
        loader.visibility = View.GONE
        movie_container.visibility  = View.VISIBLE
        Glide.with(this).load(movie.Poster).into(movie_container.movie_poster)
        movie_container.title.text  = movie.Title
        movie_container.category.text = movie.Genre
        movie_container.runtime.text = movie.Runtime
        movie_container.rating.text = String.format(resources.getString(R.string.rating),movie.imdbRating)
        movie_container.description.text = movie.Plot
        movie_container.director.text =String.format(resources.getString(R.string.director),movie.Director)
        movie_container.writer.text = String.format(resources.getString(R.string.write),movie.Writer)
        movie_container.actor.text = String.format(resources.getString(R.string.actors),movie.Actors)

        movie_container.score.text =String.format(resources.getString(R.string.score),movie.Metascore)
        movie_container.popularity.text =String.format(resources.getString(R.string.popularity),movie.imdbVotes)
        movie_container.reviews.text =String.format(resources.getString(R.string.score),movie.Awards)
    }
}