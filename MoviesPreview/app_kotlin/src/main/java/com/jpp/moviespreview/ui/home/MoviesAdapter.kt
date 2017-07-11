package com.jpp.moviespreview.ui.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jpp.moviespreview.R
import com.jpp.moviespreview.extentions.ctx
import com.jpp.moviespreview.extentions.loadUrl
import com.jpp.moviespreview.ui.model.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

/**
 * Created by jpp on 7/7/17.
 */

class MoviesAdapter(val moviesList: MutableList<Movie> = mutableListOf()) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindMovie(moviesList[position])
    }

    override fun getItemCount() = moviesList.size

    fun appendMovies(newMovies: List<Movie>) {
        val currentSize = itemCount
        moviesList.addAll(newMovies)
        notifyItemRangeChanged(currentSize, itemCount)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindMovie(movie: Movie) {
            with(movie) {
                itemView.txt_movie_title.text = movie.title
                itemView.txt_popularity.text = movie.popularity.toString()
                itemView.txt_vote_count.text = movie.voteCount.toString()
                itemView.iv_movie_poster.loadUrl(movie.posterPath)
            }
        }

    }
}


