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
    private final String mBackdropUrl;

    /*package*/ MoviePreviewItem(@NonNull MovieDto model,
                                 @NonNull String posterUrl,
                                 @NonNull String backdropUrl) {
        super(model);
        this.mPosterUrl = posterUrl;
        this.mBackdropUrl = backdropUrl;
    }


    @NonNull
    /*package*/ String getTitle() {
        return getModel().getTitle();
    }

    @NonNull
    /*package*/ String getPosterUrl() {
        return mPosterUrl;
    }

    @NonNull
    /*package*/ String getBackdropUrl() {
        return mBackdropUrl;
    }
}
