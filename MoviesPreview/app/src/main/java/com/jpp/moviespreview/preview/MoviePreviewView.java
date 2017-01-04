package com.jpp.moviespreview.preview;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.mvp.PresentingView;

import java.util.List;

/**
 * PresentingView implementation for the moview preview detail screen.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
/*package*/ interface MoviePreviewView extends PresentingView {


    /**
     * Shows the data of the movie in the MoviePreviewItem passed as parameter.
     */
    void showMovie(@NonNull MoviePreviewItem moviePreviewItem);


    /**
     * Shows the details of the movie.
     *
     * @param details - the entire list of details to show.
     */
    void showDetails(@NonNull List<MoviePreviewItemDetail> details);
}
