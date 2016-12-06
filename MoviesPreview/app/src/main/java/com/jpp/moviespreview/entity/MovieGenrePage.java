package com.jpp.moviespreview.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jpperetti on 3/12/16.
 */

public class MovieGenrePage {

    @Expose
    @SerializedName("genres")
    private List<MovieGenreDto> mGenres;


    public List<MovieGenreDto> getMovieGenres() {
        return mGenres;
    }
}
