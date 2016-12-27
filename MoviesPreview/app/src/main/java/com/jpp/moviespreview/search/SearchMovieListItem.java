package com.jpp.moviespreview.search;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.adapter.ItemHolder;
import com.jpp.moviespreview.core.entity.MovieDto;

/**
 * ItemHolder to show the search result in the search screen.
 * <p>
 * Created by jpperetti on 22/12/16.
 */
/*package*/ class SearchMovieListItem extends ItemHolder<MovieDto> {

    private final String mPosterUrl;

    SearchMovieListItem(@NonNull MovieDto model, @NonNull String posterUrl) {
        super(model);
        mPosterUrl = posterUrl;
    }


    @NonNull
    String getTitle() {
        return getModel().getTitle();
    }

    @NonNull
    String getPosterUrl() {
        return mPosterUrl;
    }

    @NonNull
    String getOverview() {
        return getModel().getOverview();
    }


}
