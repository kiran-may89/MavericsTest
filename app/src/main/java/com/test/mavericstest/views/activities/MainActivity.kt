package com.test.mavericstest.views.activities

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat.collapseActionView

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.mavericstest.R
import com.test.mavericstest.utils.ItemDecoration

import com.test.mavericstest.viewmodels.MainViewModel

import com.test.mavericstest.views.adapters.MovieAdapter
import com.test.scoop.utils.RecyclerViewTouchListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val adapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    private val viewmodel: MainViewModel by lazy {

        ViewModelProvider(this).get(MainViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initList()
        initObservers()
    }

    private fun initObservers() {
        viewmodel.movieLiveData.observe(this) { value ->
            adapter.setItems(value.Search)
        }
    }

    private fun initList() {

        movie_list.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        movie_list.adapter = adapter
        movie_list.addItemDecoration(ItemDecoration(resources.getDimensionPixelSize(R.dimen.size_8)))
        movie_list.addOnItemTouchListener(RecyclerViewTouchListener(this, movie_list) { position ->

            val intent = Intent(this, AboutMovieActivity::class.java)
            intent.putExtra(MOVIE_ID, adapter.getItem(position).imdbID)
            startActivity(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            maxWidth = Integer.MAX_VALUE
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    viewmodel.search(query)

                    collapseActionView(menu.findItem(R.id.search))
                    return false
                }

            })

        }

        return super.onCreateOptionsMenu(menu)
    }
}
