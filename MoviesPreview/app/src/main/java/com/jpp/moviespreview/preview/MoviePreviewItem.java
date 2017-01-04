package com.jpp.moviespreview.preview;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.adapter.ItemHolder;
import com.jpp.moviespreview.core.entity.MovieDto;

/**
 * ItemHolder for the preview section
 * <p>
 * Created by jpperetti on 6/12/16.
 */
/*package*/ class MoviePreviewItem extends ItemHolder<MovieDto> {

    private final String mPosterUrl;
    private final String mGenres;
    private final String mPopularity;
    private final String mBackdropUrl;

    /*package*/ MoviePreviewItem(@NonNull MovieDto model,
                                 @NonNull String posterUrl,
                                 @NonNull String genres,
                                 @NonNull String popularity,
                                 @NonNull String backdropUrl) {
        super(model);
        this.mPosterUrl = posterUrl;
        this.mGenres = genres;
        this.mPopularity = popularity;
        this.mBackdropUrl = backdropUrl;
    }


    @NonNull
    /*package*/ String getTitle() {
        return getModel().getTitle();
    }

    @NonNull
    /*package*/ String getOverview() {
        return getModel().getOverview();
    }

    @NonNull
    /*package*/ String getReleaseDate() {
        return getModel().getReleaseDate();
    }

    @NonNull
    /*package*/ String getPosterUrl() {
        return mPosterUrl;
    }

    @NonNull
    /*package*/ String getGenres() {
        return mGenres;
    }

    @NonNull
    /*package*/ String getPopularity() {
        return mPopularity;
    }

    @NonNull
    /*package*/ String getBackdropUrl() {
        return mBackdropUrl;
    }
}
