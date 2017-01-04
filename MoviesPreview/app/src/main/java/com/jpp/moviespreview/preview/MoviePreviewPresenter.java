package com.jpp.moviespreview.preview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.entity.ImagesConfigurationDto;
import com.jpp.moviespreview.core.entity.MovieDto;
import com.jpp.moviespreview.core.mvp.BasePresenter;

/**
 * BasePresenter for the movies preview section.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
/*package*/ class MoviePreviewPresenter extends BasePresenter<MoviePreviewView> {


    private MovieDto selectedMovie;

    @Override
    protected void init(@Nullable Bundle extraData) {
        super.init(extraData);
        if (extraData != null) {
            selectedMovie = PreviewInput.dettachFromExtra(extraData);
        }
    }


    @Override
    protected void linkView(@NonNull MoviePreviewView view) {
        super.linkView(view);

        ImagesConfigurationDto imagesConfiguration = getContext().getRemoteConfiguration().getImagesConfiguration();
        String posterUrl = imagesConfiguration.getPosterImageBaseUrl() + imagesConfiguration.getDefaultPosterSize() + selectedMovie.getPosterPath();
        String backdropUrl = imagesConfiguration.getPosterImageBaseUrl() + imagesConfiguration.getDefaultPosterSize() + selectedMovie.getBackdropPath();
        String genres = getContext().getGenresById(selectedMovie.getGenreIds());
        String popularity = view.getApplicationContext().getString(R.string.popularity,
                String.valueOf(selectedMovie.getPopularity()));
        MoviePreviewItem item = new MoviePreviewItem(selectedMovie, posterUrl, genres, popularity, backdropUrl);
        view.showMovie(item);
    }
}
