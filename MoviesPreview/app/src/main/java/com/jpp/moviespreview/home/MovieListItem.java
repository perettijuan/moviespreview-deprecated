package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.adapter.ItemHolder;
import com.jpp.moviespreview.core.entity.MovieDto;

/**
 * Created by jpperetti on 3/12/16.
 */

public class MovieListItem extends ItemHolder<MovieDto> {


    private final String mPosterUrl;
    private final String mGenres;
    private final String mPopularity;

    /*package*/ MovieListItem(@NonNull MovieDto model,
                              @NonNull String posterUrl,
                              @NonNull String genres,
                              @NonNull String popularity) {
        super(model);
        this.mPosterUrl = posterUrl;
        this.mGenres = genres;
        this.mPopularity = popularity;
    }


    @NonNull
    public String getTitle() {
        return getModel().getTitle();
    }

    @NonNull
    public String getReleaseDate() {
        return getModel().getReleaseDate();
    }

    @NonNull
    public String getPosterUrl() {
        return mPosterUrl;
    }

    @NonNull
    public String getGenres() {
        return mGenres;
    }

    @NonNull
    public String getPopularity() {
        return mPopularity;
    }
}
