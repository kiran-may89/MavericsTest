package com.test.mavericstest.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.mavericstest.R
import com.test.mavericstest.models.Search
import kotlinx.android.synthetic.main.movie_list_item.view.*


class MovieAdapter(private var movieList: List<Search> = emptyList()) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    fun setItems(items: List<Search>) {
        movieList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater.inflate(R.layout.movie_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }
    fun getItem(position: Int): Search = movieList[position]
    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {


        fun bind(item: Search) {
            root.movie_name.text = item.Title
            Glide.with(root.movie_poster.context).load(item.Poster)
                .into(root.movie_poster)


        }
    }
}
