package com.jpp.moviespreview.home.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.home.MovieListItem;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jpperetti on 3/12/16.
 */
public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.MovieViewHolder> {


    private List<MovieListItem> mData;


    public void swipeData(@NonNull List<MovieListItem> data) {
        mData = data;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_item, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        MovieListItem item = mData.get(position);
        holder.bindMovieData(item);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mData != null) {
            count = mData.size();
        }
        return count;
    }


    //-- view holder

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.iv_movie_poster)
        SimpleDraweeView ivMoviePoster;
        @InjectView(R.id.txt_movie_title)
        TextView txtMovieTitle;
        @InjectView(R.id.txt_movie_date)
        TextView txtMovieDate;
        @InjectView(R.id.txt_movie_genre)
        TextView txtMovieGenre;
        @InjectView(R.id.txt_movie_popularity)
        TextView txtMoviePopularity;

        MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }


        void bindMovieData(MovieListItem item) {
            Uri posterUri = Uri.parse(item.getPosterUrl());
            ivMoviePoster.setImageURI(posterUri);
            txtMovieTitle.setText(item.getTitle());
            txtMovieDate.setText(item.getReleaseDate());
            txtMovieGenre.setText(item.getGenres());
            txtMoviePopularity.setText(item.getPopularity());
        }
    }


}
