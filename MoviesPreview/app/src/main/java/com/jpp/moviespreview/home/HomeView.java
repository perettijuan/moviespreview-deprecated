package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.mvp.PresentingView;

import java.util.List;

/**
 * PresentingView implementation for the home screen.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
/* default */ interface HomeView extends PresentingView {


    /**
     * Shows the loading UI.
     */
    void showLoading();

    /**
     * Shows the current Movies page.
     */
    void showMoviesPage(@NonNull List<MovieListItem> page);

}
