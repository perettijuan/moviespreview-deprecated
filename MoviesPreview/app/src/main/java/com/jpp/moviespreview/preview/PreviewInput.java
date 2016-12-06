package com.jpp.moviespreview.preview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.entity.MovieDto;

/**
 * Helper class to provide input to the preview movie flow.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
public class PreviewInput {


    private static final String SELECTED_MOVIE_KEY = "selected_movie_key";
    private final MovieDto selectedMovie;


    /**
     * Class constructor.
     *
     * @param selectedMovie - the selected MovieDto
     */
    public PreviewInput(@NonNull MovieDto selectedMovie) {
        this.selectedMovie = selectedMovie;
    }


    /**
     * Attaches the input needed for the flow to the given Intent.
     */
    public void attachInputToIntent(@NonNull Intent intent) {
        intent.putExtra(SELECTED_MOVIE_KEY, selectedMovie);
    }


    /**
     * Extracts the MovieDto from the extra passed as parameter.
     *
     * @param extras - the extras to extract data from.
     * @return - the MovieDto to input in the flow.
     */
    /* default */
    static MovieDto dettachFromExtra(@NonNull Bundle extras) {
        return extras.getParcelable(SELECTED_MOVIE_KEY);
    }

}
