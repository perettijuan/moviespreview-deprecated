package com.jpp.moviespreview.search;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.mvp.PresentingView;

import java.util.List;

/**
 * PresentingView (MVP) for the search screen.
 * <p>
 * Created by jpperetti on 19/12/16.
 */
public interface SearchView extends PresentingView {

    /**
     * Shows the loading view.
     */
    void showLoading();


    /**
     * Disimisses the loading view.
     */
    void dismissLoading();


    /**
     * Shows the result of the search process.
     *
     * @param result - the List with the results.
     */
    void showSearchResult(@NonNull List<SearchMovieListItem> result);
}
